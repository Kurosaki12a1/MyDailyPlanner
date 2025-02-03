package com.kuro.mdp.features.settings.domain.use_case.categories

data class CategoriesUseCase(
    val loadCategoriesUseCase: LoadCategoriesUseCase,
    val addMainCategoryUseCase: AddMainCategoryUseCase,
    val addSubCategoryUseCase: AddSubCategoryUseCase,
    val checkSelectedCategoryUseCase: CheckSelectedCategoryUseCase,
    val restoreDefaultCategoriesUseCase: RestoreDefaultCategoriesUseCase,
    val updateMainCategoryUseCase: UpdateMainCategoryUseCase,
    val updateSubCategoryUseCase: UpdateSubCategoryUseCase,
    val deleteMainCategoryUseCase: DeleteMainCategoryUseCase,
    val deleteSubCategoryUseCase: DeleteSubCategoryUseCase
)