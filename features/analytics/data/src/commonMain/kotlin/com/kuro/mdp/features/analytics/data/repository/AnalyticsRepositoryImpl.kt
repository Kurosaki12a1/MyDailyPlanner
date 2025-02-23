package com.kuro.mdp.features.analytics.data.repository

import com.kuro.mdp.features.analytics.domain.models.analytics.CategoryAnalytics
import com.kuro.mdp.features.analytics.domain.models.analytics.PlanningAnalytics
import com.kuro.mdp.features.analytics.domain.models.analytics.ScheduleAnalytics
import com.kuro.mdp.features.analytics.domain.models.analytics.SubCategoryAnalytics
import com.kuro.mdp.features.analytics.domain.repository.AnalyticsRepository
import com.kuro.mdp.shared.domain.model.categories.Categories
import com.kuro.mdp.shared.domain.model.categories.SubCategory
import com.kuro.mdp.shared.domain.model.schedules.Schedule
import com.kuro.mdp.shared.domain.model.schedules.TimeTask
import com.kuro.mdp.shared.domain.repository.CategoriesRepository
import com.kuro.mdp.shared.domain.repository.ScheduleRepository
import com.kuro.mdp.shared.utils.extensions.countMonthByDays
import com.kuro.mdp.shared.utils.extensions.countWeeksByDays
import com.kuro.mdp.shared.utils.extensions.duration
import com.kuro.mdp.shared.utils.extensions.endOfCurrentMonth
import com.kuro.mdp.shared.utils.extensions.extractAllItem
import com.kuro.mdp.shared.utils.extensions.isCurrentDay
import com.kuro.mdp.shared.utils.extensions.isIncludeTime
import com.kuro.mdp.shared.utils.extensions.mapToDate
import com.kuro.mdp.shared.utils.extensions.shiftDays
import com.kuro.mdp.shared.utils.extensions.startThisDay
import com.kuro.mdp.shared.utils.functional.Constants
import com.kuro.mdp.shared.utils.functional.ResultState
import com.kuro.mdp.shared.utils.functional.TimePeriod
import com.kuro.mdp.shared.utils.functional.TimeRange
import com.kuro.mdp.shared.utils.functional.wrapFlow
import com.kuro.mdp.shared.utils.managers.DateManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.datetime.LocalDateTime

/**
 * Created by: minhthinh.h on 2/18/2025
 *
 * Description:
 */
class AnalyticsRepositoryImpl(
    private val scheduleRepository: ScheduleRepository,
    private val categoriesRepository: CategoriesRepository,
    private val dateManager: DateManager
) : AnalyticsRepository {

    private val currentDate: LocalDateTime
        get() = dateManager.fetchCurrentDate()

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun fetchAnalytics(period: TimePeriod): Flow<ResultState<ScheduleAnalytics>> = wrapFlow {
        scheduleRepository.fetchSchedulesByRange(null).flatMapLatest { allSchedules ->
            categoriesRepository.fetchCategories().map { allCategories ->
                val shiftAmount = period.convertToDays()
                val startDate = currentDate.startThisDay().shiftDays(-shiftAmount)
                val globalTimeRange = TimeRange(from = startDate, to = currentDate)

                val periodSchedules = allSchedules.filter { globalTimeRange.isIncludeTime(it.date.mapToDate()) }
                val timeTasks = mutableListOf<TimeTask>().apply {
                    periodSchedules.forEach { schedule ->
                        addAll(schedule.timeTasks.filter { it.isConsiderInStatistics })
                    }
                }

                val workLoadMap = countWorkLoad(period, timeTasks)
                val categoriesAnalytics = countCategoriesAnalytic(
                    categories = allCategories,
                    timeTasks = timeTasks,
                )
                val planningAnalytics = countPlanningAnalytics(allSchedules)
                val totalTasksCount = timeTasks.count { it.isConsiderInStatistics }
                val totalTasksTime = timeTasks.map { it.timeRange }.sumOf { duration(it) }
                val averageDayLoad = if (periodSchedules.isNotEmpty()) totalTasksCount / periodSchedules.size else 0
                val averageTaskTime = if (totalTasksCount != 0) totalTasksTime / totalTasksCount else 0L

                return@map ScheduleAnalytics(
                    dateWorkLoadMap = workLoadMap,
                    categoriesAnalytics = categoriesAnalytics,
                    planningAnalytics = planningAnalytics,
                    totalTasksCount = totalTasksCount,
                    totalTasksTime = totalTasksTime,
                    averageDayLoad = averageDayLoad,
                    averageTaskTime = averageTaskTime,
                )
            }
        }
    }

    private fun countWorkLoad(period: TimePeriod, timeTasks: List<TimeTask>): Map<TimeRange, List<TimeTask>> {
        val shiftAmount = period.convertToDays()
        val startDate = currentDate.startThisDay().shiftDays(-shiftAmount)

        val periodCount = when (period) {
            TimePeriod.WEEK -> Constants.Date.DAY
            TimePeriod.MONTH -> Constants.Date.DAYS_IN_WEEK
            TimePeriod.HALF_YEAR -> Constants.Date.DAYS_IN_MONTH
            TimePeriod.YEAR -> Constants.Date.DAYS_IN_MONTH
        }
        val periodValue = when (period) {
            TimePeriod.WEEK -> Constants.Date.DAYS_IN_WEEK
            TimePeriod.MONTH -> countWeeksByDays(shiftAmount)
            TimePeriod.HALF_YEAR -> countMonthByDays(shiftAmount)
            TimePeriod.YEAR -> countMonthByDays(shiftAmount)
        }
        val workLoadPeriodMap = mutableMapOf<TimeRange, List<TimeTask>>().apply {
            repeat(periodValue) {
                val actualStartDate = startDate.shiftDays(1)
                val start = actualStartDate.shiftDays(periodCount * it)
                val end = actualStartDate.shiftDays(periodCount * (it + 1))
                put(TimeRange(start, end), emptyList())
            }
        }

        timeTasks.forEach { task ->
            workLoadPeriodMap.keys.findLast { it.isIncludeTime(task.date) }?.let {
                val tasks = checkNotNull(workLoadPeriodMap[it]).toMutableList()
                workLoadPeriodMap[it] = tasks.apply { add(task) }
            }
        }
        return workLoadPeriodMap
    }

    private fun countCategoriesAnalytic(
        categories: List<Categories>,
        timeTasks: List<TimeTask>,
    ) = mutableListOf<CategoryAnalytics>().let { analytics ->
        categories.onEach { categories ->
            val subCategories = categories.subCategories.toMutableList().apply {
                add(SubCategory(mainCategory = categories.category))
            }
            val categoriesAnalytic = CategoryAnalytics(
                mainCategory = categories.category,
                subCategoriesInfo = subCategories.map { SubCategoryAnalytics(subCategory = it) },
            )
            analytics.add(categoriesAnalytic)
        }

        timeTasks.filter { it.isCompleted }.forEach { timeTask ->
            val currentDuration = duration(timeTask.timeRange)
            val analyticModel = analytics.find { it.mainCategory.id == timeTask.category.id }
            if (analyticModel != null) {
                val index = analytics.indexOf(analyticModel)
                val duration = analyticModel.duration + currentDuration
                val subAnalytics = analyticModel.subCategoriesInfo.toMutableList().apply {
                    val subModel = find { it.subCategory.id == (timeTask.subCategory?.id ?: -1) }
                    if (subModel != null) {
                        val subDuration = subModel.duration + currentDuration
                        set(indexOf(subModel), subModel.copy(duration = subDuration))
                    }
                }
                analytics[index] = analyticModel.copy(
                    duration = duration,
                    subCategoriesInfo = subAnalytics,
                )
            }
        }
        return@let analytics.sortedBy { it.duration }.reversed()
    }

    private fun countPlanningAnalytics(allSchedules: List<Schedule>): Map<Int, List<PlanningAnalytics>> {
        val currentDate = dateManager.fetchCurrentDate()
        val startedDay = currentDate.endOfCurrentMonth()
        val rawAnalytics = mutableListOf<PlanningAnalytics>().apply {
            val allTimeTasks = allSchedules.map { it.timeTasks }.extractAllItem()
            for (day in 0..Constants.Date.DAYS_IN_HALF_YEAR) {
                val planningDate = startedDay.shiftDays(-day)
                val planningTimeTasks = allTimeTasks.filter {
                    val createdAt = it.createdAt ?: return@filter false
                    createdAt.isCurrentDay(planningDate) && createdAt.time <= currentDate.time
                }
                add(PlanningAnalytics(planningDate, planningTimeTasks))
            }
        }
        return rawAnalytics.groupBy { it.date.monthNumber }.mapValues { entry ->
            entry.value.sortedBy { analytic -> analytic.date }
        }
    }
}