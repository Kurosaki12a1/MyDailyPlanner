package com.kuro.mdp.shared.presentation.theme.resources

import androidx.compose.runtime.staticCompositionLocalOf
import com.kuro.mdp.shared.presentation.AppLanguage

data class AppStrings(
    val appName: String,
    val startTaskNotifyText: String,
    val timeTaskChannelName: String,
    val categoryWorkTitle: String,
    val categoryRestTitle: String,
    val categorySportTitle: String,
    val categorySleepTitle: String,
    val categoryCultureTitle: String,
    val categoryChoresTitle: String,
    val categoryTransportTitle: String,
    val categoryStudyTitle: String,
    val categoryEatTitle: String,
    val categoryEntertainmentsTitle: String,
    val categoryHygieneTitle: String,
    val categoryHealthTitle: String,
    val categoryShoppingTitle: String,
    val categoryOtherTitle: String,
    val minutesSymbol: String,
    val hoursSymbol: String,
    val separator: String,
    val cancelTitle: String,
    val confirmTitle: String,
    val okConfirmTitle: String,
    val categoryEmptyTitle: String,
    val expandedViewToggleTitle: String,
    val compactViewToggleTitle: String,
    val warningDialogTitle: String,
    val warningDeleteConfirmTitle: String,
    val hoursTitle: String,
    val minutesTitle: String,
    val dayTitle: String,
    val homeTabTitle: String,
    val analyticsTabTitle: String,
    val settingsTabTitle: String,
    val mainDrawerTitle: String,
    val overviewDrawerTitle: String,
    val drawerMainSection: String,
    val templateDrawerTitle: String,
    val categoriesDrawerTitle: String,
    val splitFormat: String,
    val amFormatTitle: String,
    val pmFormatTitle: String,
    val repeatTimeDayInMonthTitle: String,
    val repeatTimeDayInWeekTitle: String,
    val repeatTimeWeekDayInMonthTitle: String,
    val repeatTimeDayInYearTitle: String,
    val sundayTitle: String,
    val mondayTitle: String,
    val tuesdayTitle: String,
    val wednesdayTitle: String,
    val thursdayTitle: String,
    val fridayTitle: String,
    val saturdayTitle: String,
    val januaryTitle: String,
    val februaryTitle: String,
    val marchTitle: String,
    val aprilTitle: String,
    val mayTitle: String,
    val juneTitle: String,
    val julyTitle: String,
    val augustTitle: String,
    val septemberTitle: String,
    val octoberTitle: String,
    val novemberTitle: String,
    val decemberTitle: String,
    val beforeTaskNotifyText: String,
    val afterTaskNotifyText: String,
    val priorityStandard: String,
    val priorityMedium: String,
    val priorityMax: String,
    val emptyScheduleTitle: String,
)

internal val russianAppString = AppStrings(
    appName = "Time Planner",
    startTaskNotifyText = "Начало события",
    timeTaskChannelName = "События",
    categoryWorkTitle = "Работа",
    categoryRestTitle = "Отдых",
    categorySportTitle = "Спорт",
    categorySleepTitle = "Сон",
    categoryCultureTitle = "Культура",
    categoryChoresTitle = "Дела",
    categoryTransportTitle = "Транспорт",
    categoryStudyTitle = "Учёба",
    categoryEatTitle = "Приём пищи",
    categoryEntertainmentsTitle = "Развлечение",
    categoryHygieneTitle = "Личная гигиена",
    categoryHealthTitle = "Медицина",
    categoryShoppingTitle = "Шопинг",
    categoryOtherTitle = "Разное",
    minutesSymbol = "м",
    hoursSymbol = "ч",
    separator = ":",
    cancelTitle = "Отменить",
    confirmTitle = "Выбрать",
    okConfirmTitle = "ОК",
    categoryEmptyTitle = "Отсутствует",
    expandedViewToggleTitle = "Расширенный вид",
    compactViewToggleTitle = "Компактный вид",
    warningDialogTitle = "Предупреждение!",
    warningDeleteConfirmTitle = "Удалить",
    hoursTitle = "Часы",
    minutesTitle = "Минуты",
    dayTitle = "дн",
    homeTabTitle = "Главная",
    analyticsTabTitle = "Аналитика",
    settingsTabTitle = "Настройки",
    mainDrawerTitle = "Главная",
    overviewDrawerTitle = "Обзор",
    drawerMainSection = "Планы",
    templateDrawerTitle = "Шаблоны",
    categoriesDrawerTitle = "Категории",
    splitFormat = "%s | %s",
    amFormatTitle = "AM",
    pmFormatTitle = "PM",
    repeatTimeDayInMonthTitle = "Каждый месяц",
    repeatTimeDayInWeekTitle = "Каждую неделю",
    repeatTimeWeekDayInMonthTitle = "Каждую неделю месяца",
    repeatTimeDayInYearTitle = "Каждый год",
    sundayTitle = "Воскресенье",
    mondayTitle = "Понедельник",
    tuesdayTitle = "Вторник",
    wednesdayTitle = "Среда",
    thursdayTitle = "Четверг",
    fridayTitle = "Пятница",
    saturdayTitle = "Суббота",
    januaryTitle = "Январь",
    februaryTitle = "Февраль",
    marchTitle = "Март",
    aprilTitle = "Апрель",
    mayTitle = "Май",
    juneTitle = "Июнь",
    julyTitle = "Июль",
    augustTitle = "Август",
    septemberTitle = "Сентябрь",
    octoberTitle = "Октябрь",
    novemberTitle = "Ноябрь",
    decemberTitle = "Декабрь",
    beforeTaskNotifyText = "Напоминание о приближающемся событии",
    afterTaskNotifyText = "Конец события",
    priorityStandard = "Обычный",
    priorityMedium = "Средний",
    priorityMax = "Макс.",
    emptyScheduleTitle = "План отсутствует",
)

internal val englishAppString = AppStrings(
    appName = "Time Planner",
    startTaskNotifyText = "The beginning of the event",
    timeTaskChannelName = "Events",
    categoryWorkTitle = "Work",
    categoryRestTitle = "Rest",
    categorySportTitle = "Sport",
    categorySleepTitle = "Sleep",
    categoryCultureTitle = "Culture",
    categoryChoresTitle = "Chores",
    categoryTransportTitle = "Transport",
    categoryStudyTitle = "Study",
    categoryEatTitle = "Eating",
    categoryEntertainmentsTitle = "Entertainment",
    categoryHygieneTitle = "Self-Care",
    categoryHealthTitle = "Health",
    categoryShoppingTitle = "Shopping",
    categoryOtherTitle = "Misc",
    minutesSymbol = "m",
    hoursSymbol = "h",
    separator = ":",
    cancelTitle = "Cancel",
    confirmTitle = "Select",
    okConfirmTitle = "OK",
    categoryEmptyTitle = "Absent",
    expandedViewToggleTitle = "Expanded view",
    compactViewToggleTitle = "Compact view",
    warningDialogTitle = "Warning!",
    warningDeleteConfirmTitle = "Delete",
    hoursTitle = "Hours",
    minutesTitle = "Minutes",
    dayTitle = "d.",
    homeTabTitle = "Home",
    analyticsTabTitle = "Analytics",
    settingsTabTitle = "Settings",
    mainDrawerTitle = "Main",
    overviewDrawerTitle = "Overview",
    drawerMainSection = "Plans",
    templateDrawerTitle = "Templates",
    categoriesDrawerTitle = "Categories",
    splitFormat = "%s | %s",
    amFormatTitle = "AM",
    pmFormatTitle = "PM",
    repeatTimeDayInMonthTitle = "Every month",
    repeatTimeDayInWeekTitle = "Every week",
    repeatTimeWeekDayInMonthTitle = "Every week of the month",
    repeatTimeDayInYearTitle = "Every year",
    sundayTitle = "Sunday",
    mondayTitle = "Monday",
    tuesdayTitle = "Tuesday",
    wednesdayTitle = "Wednesday",
    thursdayTitle = "Thursday",
    fridayTitle = "Friday",
    saturdayTitle = "Saturday",
    januaryTitle = "January",
    februaryTitle = "February",
    marchTitle = "March",
    aprilTitle = "April",
    mayTitle = "May",
    juneTitle = "June",
    julyTitle = "July",
    augustTitle = "August",
    septemberTitle = "September",
    octoberTitle = "October",
    novemberTitle = "November",
    decemberTitle = "December",
    beforeTaskNotifyText = "Reminder of an upcoming event",
    afterTaskNotifyText = "End of the event",
    priorityStandard = "Standard",
    priorityMedium = "Medium",
    priorityMax = "Max",
    emptyScheduleTitle = "Nothing planned yet",
)

internal val germanAppString = AppStrings(
    appName = "Time Planner",
    startTaskNotifyText = "Beginn eines Ereignisses",
    timeTaskChannelName = "Ereignisse",
    categoryWorkTitle = "Arbeit",
    categoryRestTitle = "Erholung",
    categorySportTitle = "Sport",
    categorySleepTitle = "Schlaf",
    categoryCultureTitle = "Kultur",
    categoryChoresTitle = "Haushaltsarbeiten",
    categoryTransportTitle = "Verkehr",
    categoryStudyTitle = "Studium",
    categoryEatTitle = "Mahlzeit",
    categoryEntertainmentsTitle = "Unterhaltung",
    categoryHygieneTitle = "Persönliche Hygiene",
    categoryHealthTitle = "Gesundheit",
    categoryShoppingTitle = "Einkaufen",
    categoryOtherTitle = "Diverses",
    minutesSymbol = "m",
    hoursSymbol = "h",
    separator = ":",
    cancelTitle = "Schließen",
    confirmTitle = "Wählen",
    okConfirmTitle = "OK",
    categoryEmptyTitle = "Leer",
    expandedViewToggleTitle = "Erweiterte Ansicht",
    compactViewToggleTitle = "Kompakte Ansicht",
    warningDialogTitle = "Warnung!",
    warningDeleteConfirmTitle = "Entfernen",
    hoursTitle = "Uhr",
    minutesTitle = "Minuten",
    dayTitle = "d.",
    homeTabTitle = "Start",
    analyticsTabTitle = "Analyse",
    settingsTabTitle = "Einstellungen",
    mainDrawerTitle = "Startseite",
    overviewDrawerTitle = "Überblick",
    drawerMainSection = "Pläne",
    templateDrawerTitle = "Muster",
    categoriesDrawerTitle = "Kategorien",
    splitFormat = "%s | %s",
    amFormatTitle = "AM",
    pmFormatTitle = "PM",
    repeatTimeDayInMonthTitle = "Jeden Monat",
    repeatTimeDayInWeekTitle = "Jede Woche",
    repeatTimeWeekDayInMonthTitle = "Jede Woche des Monats",
    repeatTimeDayInYearTitle = "Jedes Jahr",
    sundayTitle = "Sonntag",
    mondayTitle = "Montag",
    tuesdayTitle = "Dienstag",
    wednesdayTitle = "Mittwoch",
    thursdayTitle = "Donnerstag",
    fridayTitle = "Freitag",
    saturdayTitle = "Samstag",
    januaryTitle = "Januar",
    februaryTitle = "Februar",
    marchTitle = "März",
    aprilTitle = "April",
    mayTitle = "Mai",
    juneTitle = "Juni",
    julyTitle = "Juli",
    augustTitle = "August",
    septemberTitle = "September",
    octoberTitle = "Oktober",
    novemberTitle = "November",
    decemberTitle = "Dezember",
    beforeTaskNotifyText = "Erinnerung an ein nahendes Ereignis",
    afterTaskNotifyText = "Ende des Ereignisses",
    priorityStandard = "Gewöhnlich",
    priorityMedium = "Mittel",
    priorityMax = "Max.",
    emptyScheduleTitle = "Kein Plan",
)

internal val spanishAppString = AppStrings(
    appName = "Time Planner",
    startTaskNotifyText = "Inicio del evento",
    timeTaskChannelName = "Eventos",
    categoryWorkTitle = "Trabajo",
    categoryRestTitle = "Tiempo libre",
    categorySportTitle = "Deporte",
    categorySleepTitle = "Descanso",
    categoryCultureTitle = "Cultura",
    categoryChoresTitle = "Tareas Domésticas",
    categoryTransportTitle = "Viajes",
    categoryStudyTitle = "Estudio",
    categoryEatTitle = "Comidas",
    categoryEntertainmentsTitle = "Entretenimiento",
    categoryHygieneTitle = "Aseo personal",
    categoryHealthTitle = "Medicina",
    categoryShoppingTitle = "Compras",
    categoryOtherTitle = "Varios",
    minutesSymbol = "m",
    hoursSymbol = "h",
    separator = ":",
    cancelTitle = "Cancelar",
    confirmTitle = "Guardar",
    okConfirmTitle = "Vale",
    categoryEmptyTitle = "Ninguna",
    expandedViewToggleTitle = "Vista ampliada",
    compactViewToggleTitle = "Vista compacta",
    warningDialogTitle = "¡Cuidado!",
    warningDeleteConfirmTitle = "Eliminar",
    hoursTitle = "Horas",
    minutesTitle = "Minutos",
    dayTitle = "d.",
    homeTabTitle = "Inicio",
    analyticsTabTitle = "Estadísticas",
    settingsTabTitle = "Ajustes",
    mainDrawerTitle = "Página principal",
    overviewDrawerTitle = "Visión general",
    drawerMainSection = "Planes",
    templateDrawerTitle = "Plantillas",
    categoriesDrawerTitle = "Categorías",
    splitFormat = "%s | %s",
    amFormatTitle = "AM",
    pmFormatTitle = "PM",
    repeatTimeDayInMonthTitle = "Cada mes",
    repeatTimeDayInWeekTitle = "Cada día",
    repeatTimeWeekDayInMonthTitle = "Cada semana",
    repeatTimeDayInYearTitle = "Cada año",
    sundayTitle = "Domingo",
    mondayTitle = "Lunes",
    tuesdayTitle = "Martes",
    wednesdayTitle = "Miércoles",
    thursdayTitle = "Jueves",
    fridayTitle = "Viernes",
    saturdayTitle = "Sábado",
    januaryTitle = "Enero",
    februaryTitle = "Febrero",
    marchTitle = "Marzo",
    aprilTitle = "Abril",
    mayTitle = "Mayo",
    juneTitle = "Junio",
    julyTitle = "Julio",
    augustTitle = "Agosto",
    septemberTitle = "Septiembre",
    octoberTitle = "Octubre",
    novemberTitle = "Noviembre",
    decemberTitle = "Diciembre",
    beforeTaskNotifyText = "Recordatorio de un evento inminente",
    afterTaskNotifyText = "Fin del evento",
    priorityStandard = "Normal",
    priorityMedium = "Promedio",
    priorityMax = "Máximo",
    emptyScheduleTitle = "Nada planeado todavía",
)

internal val persianAppString = AppStrings(
    appName = "Time Planner",
    startTaskNotifyText = "آغاز رویداد",
    timeTaskChannelName = "رویدادها",
    categoryWorkTitle = "کار",
    categoryRestTitle = "استراحت کن",
    categorySportTitle = "ورزش",
    categorySleepTitle = "خواب",
    categoryCultureTitle = "فرهنگ",
    categoryChoresTitle = "کارهای خانه",
    categoryTransportTitle = "حمل و نقل",
    categoryStudyTitle = "مطالعه",
    categoryEatTitle = "غذا خوردن",
    categoryEntertainmentsTitle = "سرگرمی",
    categoryHygieneTitle = "بهداشت فردی",
    categoryHealthTitle = "دارو",
    categoryShoppingTitle = "خرید",
    categoryOtherTitle = "متفرقه",
    minutesSymbol = "m",
    hoursSymbol = "h",
    separator = ":",
    cancelTitle = "لغو",
    confirmTitle = "انتخاب کنید",
    okConfirmTitle = "باشه",
    categoryEmptyTitle = "غایب",
    expandedViewToggleTitle = "نمای گسترده",
    compactViewToggleTitle = "نمای جمع و جور",
    warningDialogTitle = "هشدار!",
    warningDeleteConfirmTitle = "حذف کنید",
    hoursTitle = "تماشا کنید",
    minutesTitle = "دقیقه",
    dayTitle = "d.",
    homeTabTitle = "اصلی",
    analyticsTabTitle = "تجزیه و تحلیل",
    settingsTabTitle = "تنظیمات",
    mainDrawerTitle = "اصلی",
    overviewDrawerTitle = "نمای کلی",
    drawerMainSection = "برنامه ها",
    templateDrawerTitle = "قالب ها",
    categoriesDrawerTitle = "دسته بندی ها",
    splitFormat = "%s | %s",
    amFormatTitle = "",
    pmFormatTitle = "PM",
    repeatTimeDayInMonthTitle = "هر ماه",
    repeatTimeDayInWeekTitle = "هر هفته",
    repeatTimeWeekDayInMonthTitle = "هر هفته از ماه",
    repeatTimeDayInYearTitle = "هر سال",
    sundayTitle = "یکشنبه",
    mondayTitle = "دوشنبه",
    tuesdayTitle = "سه شنبه",
    wednesdayTitle = "چهارشنبه",
    thursdayTitle = "پنجشنبه",
    fridayTitle = "جمعه",
    saturdayTitle = "شنبه",
    januaryTitle = "ژانویه",
    februaryTitle = "فوریه",
    marchTitle = "مارس",
    aprilTitle = "آوریل",
    mayTitle = "ممکن است",
    juneTitle = "ژوئن",
    julyTitle = "جولای",
    augustTitle = "آگوست",
    septemberTitle = "سپتامبر",
    octoberTitle = "اکتبر",
    novemberTitle = "نوامبر",
    decemberTitle = "دسامبر",
    beforeTaskNotifyText = "یادآوری یک رویداد آینده",
    afterTaskNotifyText = "پایان رویداد",
    priorityStandard = "استاندارد",
    priorityMedium = "میانگین",
    priorityMax = "حداکثر",
    emptyScheduleTitle = "هنوز هیچ چیز برنامه ریزی نشده است",
)

internal val frenchAppString = AppStrings(
    appName = "Time Planner",
    startTaskNotifyText = "Début de l'évènement",
    timeTaskChannelName = "Évènement",
    categoryWorkTitle = "Travail",
    categoryRestTitle = "Repos",
    categorySportTitle = "Sport",
    categorySleepTitle = "Sommeil",
    categoryCultureTitle = "Culture",
    categoryChoresTitle = "Corvées",
    categoryTransportTitle = "Déplacement",
    categoryStudyTitle = "Études",
    categoryEatTitle = "Repas",
    categoryEntertainmentsTitle = "Divertissement",
    categoryHygieneTitle = "Hygiène personnelle",
    categoryHealthTitle = "Médecine",
    categoryShoppingTitle = "Shopping",
    categoryOtherTitle = "Divers",
    minutesSymbol = "m",
    hoursSymbol = "h",
    separator = ":",
    cancelTitle = "Annuler",
    confirmTitle = "Sélectionner",
    okConfirmTitle = "OK",
    categoryEmptyTitle = "Absent",
    expandedViewToggleTitle = "Affichage étendu",
    compactViewToggleTitle = "Affichage compact",
    warningDialogTitle = "Avertissement!",
    warningDeleteConfirmTitle = "Supprimer",
    hoursTitle = "Heures",
    minutesTitle = "Minutes",
    dayTitle = "d.",
    homeTabTitle = "Accueil",
    analyticsTabTitle = "Statistiques",
    settingsTabTitle = "Paramètres",
    mainDrawerTitle = "Principal",
    overviewDrawerTitle = "Vue d'Ensemble",
    drawerMainSection = "Plans",
    templateDrawerTitle = "Modèles",
    categoriesDrawerTitle = "Categories",
    splitFormat = "%s | %s",
    amFormatTitle = "",
    pmFormatTitle = "",
    repeatTimeDayInMonthTitle = "Tout les mois",
    repeatTimeDayInWeekTitle = "Toutes les semaines",
    repeatTimeWeekDayInMonthTitle = "Toutes les semaines du mois",
    repeatTimeDayInYearTitle = "Tout les ans",
    sundayTitle = "Dimanche",
    mondayTitle = "Lundi",
    tuesdayTitle = "Mardi",
    wednesdayTitle = "Mercredi",
    thursdayTitle = "Jeudi",
    fridayTitle = "Vendredi",
    saturdayTitle = "Samedi",
    januaryTitle = "Janvier",
    februaryTitle = "Février",
    marchTitle = "Mars",
    aprilTitle = "Avril",
    mayTitle = "Mai",
    juneTitle = "Juin",
    julyTitle = "Juillet",
    augustTitle = "Août",
    septemberTitle = "Septembre",
    octoberTitle = "Octobre",
    novemberTitle = "Novembre",
    decemberTitle = "Décembre",
    beforeTaskNotifyText = "Rappel de l'événement qui approche",
    afterTaskNotifyText = "Fin de l'événement",
    priorityStandard = "Ordinaire",
    priorityMedium = "Moyen",
    priorityMax = "Max.",
    emptyScheduleTitle = "Rien de prévu pour le moment",
)

internal val brazilianPortugueseAppString = AppStrings(
    appName = "Time Planner",
    startTaskNotifyText = "Início do evento",
    timeTaskChannelName = "Eventos",
    categoryWorkTitle = "Trabalho",
    categoryRestTitle = "Descansar",
    categorySportTitle = "Esporte",
    categorySleepTitle = "Dormir",
    categoryCultureTitle = "Cultura",
    categoryChoresTitle = "Tarefas",
    categoryTransportTitle = "Transporte",
    categoryStudyTitle = "Estudar",
    categoryEatTitle = "Comer",
    categoryEntertainmentsTitle = "Entretenimentos",
    categoryHygieneTitle = "Cuidados Pessoais",
    categoryHealthTitle = "Medicina",
    categoryShoppingTitle = "Compras",
    categoryOtherTitle = "Diversos",
    minutesSymbol = "m",
    hoursSymbol = "h",
    separator = ":",
    cancelTitle = "Cancelar",
    confirmTitle = "Selecionar",
    okConfirmTitle = "OK",
    categoryEmptyTitle = "Vazio",
    expandedViewToggleTitle = "Visão expandida",
    compactViewToggleTitle = "Visão comprimida",
    warningDialogTitle = "Atenção!",
    warningDeleteConfirmTitle = "Deletar",
    hoursTitle = "Horas",
    minutesTitle = "Minutos",
    dayTitle = "dias",
    homeTabTitle = "Início",
    analyticsTabTitle = "Análises",
    settingsTabTitle = "Configurações",
    mainDrawerTitle = "Principal",
    overviewDrawerTitle = "Visão geral",
    drawerMainSection = "Planos",
    templateDrawerTitle = "Templates",
    categoriesDrawerTitle = "Categorias",
    splitFormat = "%s | %s",
    amFormatTitle = "AM",
    pmFormatTitle = "PM",
    repeatTimeDayInMonthTitle = "Todo mês",
    repeatTimeDayInWeekTitle = "Toda semana",
    repeatTimeWeekDayInMonthTitle = "Todas as semanas do mês",
    repeatTimeDayInYearTitle = "Todo ano",
    sundayTitle = "Domingo",
    mondayTitle = "Segunda",
    tuesdayTitle = "Terça",
    wednesdayTitle = "Quarta",
    thursdayTitle = "Quinta",
    fridayTitle = "Sexta",
    saturdayTitle = "Sábado",
    januaryTitle = "Janeiro",
    februaryTitle = "Fevereiro",
    marchTitle = "Março",
    aprilTitle = "Abril",
    mayTitle = "Maio",
    juneTitle = "Junho",
    julyTitle = "Julho",
    augustTitle = "Agosto",
    septemberTitle = "Setembro",
    octoberTitle = "Outubro",
    novemberTitle = "Novembro",
    decemberTitle = "Dezembro",
    beforeTaskNotifyText = "Lembrete de um próximo evento",
    afterTaskNotifyText = "Fim do evento",
    priorityStandard = "Usual",
    priorityMedium = "Média",
    priorityMax = "Máximo",
    emptyScheduleTitle = "Nada planejado ainda",
)

internal val turkishAppString = AppStrings(
    appName = "Zaman Planlayıcı",
    startTaskNotifyText = "Etkinliğin başlangıcı",
    timeTaskChannelName = "Etkinlikler",
    categoryWorkTitle = "İş",
    categoryRestTitle = "Mola",
    categorySportTitle = "Spor",
    categorySleepTitle = "Uyku",
    categoryCultureTitle = "Kültür",
    categoryChoresTitle = "Ev İşleri",
    categoryTransportTitle = "Ulaşım",
    categoryStudyTitle = "Çalışma",
    categoryEatTitle = "Yemek",
    categoryEntertainmentsTitle = "Eğlence",
    categoryHygieneTitle = "Kişisel Bakım",
    categoryHealthTitle = "Sağlık",
    categoryShoppingTitle = "Alışveriş",
    categoryOtherTitle = "Diğer",
    minutesSymbol = "dak",
    hoursSymbol = "sa",
    separator = ":",
    cancelTitle = "İptal",
    confirmTitle = "Seç",
    okConfirmTitle = "Tamam",
    categoryEmptyTitle = "Yok",
    expandedViewToggleTitle = "Genişletilmiş görünüm",
    compactViewToggleTitle = "Kompakt görünüm",
    warningDialogTitle = "Uyarı!",
    warningDeleteConfirmTitle = "Sil",
    hoursTitle = "Saat",
    minutesTitle = "Dakika",
    dayTitle = "gün",
    homeTabTitle = "Ana Sayfa",
    analyticsTabTitle = "Analiz",
    settingsTabTitle = "Ayarlar",
    mainDrawerTitle = "Ana",
    overviewDrawerTitle = "Genel Bakış",
    drawerMainSection = "Planlar",
    templateDrawerTitle = "Şablonlar",
    categoriesDrawerTitle = "Kategoriler",
    splitFormat = "%s | %s",
    amFormatTitle = "ÖÖ",
    pmFormatTitle = "ÖS",
    repeatTimeDayInMonthTitle = "Her ay",
    repeatTimeDayInWeekTitle = "Her hafta",
    repeatTimeWeekDayInMonthTitle = "Her ayın haftası",
    repeatTimeDayInYearTitle = "Her yıl",
    sundayTitle = "Pazar",
    mondayTitle = "Pazartesi",
    tuesdayTitle = "Salı",
    wednesdayTitle = "Çarşamba",
    thursdayTitle = "Perşembe",
    fridayTitle = "Cuma",
    saturdayTitle = "Cumartesi",
    januaryTitle = "Ocak",
    februaryTitle = "Şubat",
    marchTitle = "Mart",
    aprilTitle = "Nisan",
    mayTitle = "Mayıs",
    juneTitle = "Haziran",
    julyTitle = "Temmuz",
    augustTitle = "Ağustos",
    septemberTitle = "Eylül",
    octoberTitle = "Ekim",
    novemberTitle = "Kasım",
    decemberTitle = "Aralık",
    beforeTaskNotifyText = "Yaklaşan bir etkinlik hatırlatması",
    afterTaskNotifyText = "Etkinliğin sonu",
    priorityStandard = "Normal",
    priorityMedium = "Orta",
    priorityMax = "Azami",
    emptyScheduleTitle = "Henüz planlanmış bir şey yok",
)

internal val vietnameseAppString = AppStrings(
    appName = "Time Planner",
    startTaskNotifyText = "Khởi đầu của sự kiện",
    timeTaskChannelName = "Sự kiện",
    categoryWorkTitle = "Công việc",
    categoryRestTitle = "Nghỉ ngơi",
    categorySportTitle = "Thể thao",
    categorySleepTitle = "Ngủ",
    categoryCultureTitle = "Văn hoá",
    categoryChoresTitle = "Việc vặt",
    categoryTransportTitle = "Chuyên chở",
    categoryStudyTitle = "Học tập",
    categoryEatTitle = "Ăn",
    categoryEntertainmentsTitle = "Giải trí",
    categoryHygieneTitle = "Chăm sóc cá nhân",
    categoryHealthTitle = "Thuốc",
    categoryShoppingTitle = "Mua sắm",
    categoryOtherTitle = "Thứ khác",
    minutesSymbol = "m",
    hoursSymbol = "h",
    separator = ":",
    cancelTitle = "Hủy bỏ",
    confirmTitle = "Lựa chọn",
    okConfirmTitle = "OK",
    categoryEmptyTitle = "Vắng mặt",
    expandedViewToggleTitle = "Chế độ xem mở rộng",
    compactViewToggleTitle = "Chế độ xem thu gọn",
    warningDialogTitle = "Cảnh báo!",
    warningDeleteConfirmTitle = "Xóa bỏ",
    hoursTitle = "Giờ",
    minutesTitle = "Phút",
    dayTitle = "d.",
    homeTabTitle = "Trang chủ",
    analyticsTabTitle = "Phân tích",
    settingsTabTitle = "Thiết đặt",
    mainDrawerTitle = "Chủ yếu",
    overviewDrawerTitle = "Tổng quan",
    drawerMainSection = "Các kế hoạch",
    templateDrawerTitle = "Mẫu",
    categoriesDrawerTitle = "Danh mục",
    splitFormat = "%s %s",
    amFormatTitle = "AM",
    pmFormatTitle = "PM",
    repeatTimeDayInMonthTitle = "Mỗi tháng",
    repeatTimeDayInWeekTitle = "Mỗi tuần",
    repeatTimeWeekDayInMonthTitle = "Mỗi tuần trong tháng",
    repeatTimeDayInYearTitle = "Mỗi năm",
    sundayTitle = "Chủ nhật",
    mondayTitle = "Thứ hai",
    tuesdayTitle = "Thứ ba",
    wednesdayTitle = "Thứ Tư",
    thursdayTitle = "Thứ năm",
    fridayTitle = "Thứ sáu",
    saturdayTitle = "Thứ bảy",
    januaryTitle = "Tháng giêng",
    februaryTitle = "Tháng hai",
    marchTitle = "Tháng ba",
    aprilTitle = "Tháng tư",
    mayTitle = "Tháng năm",
    juneTitle = "Tháng sáu",
    julyTitle = "Tháng bảy",
    augustTitle = "Tháng tám",
    septemberTitle = "Tháng chín",
    octoberTitle = "Tháng mười",
    novemberTitle = "Tháng mười một",
    decemberTitle = "Tháng mười hai",
    beforeTaskNotifyText = "Lời nhắc về sự kiện sắp tới",
    afterTaskNotifyText = "Kết thúc sự kiện",
    priorityStandard = "Thông thường",
    priorityMedium = "Trung bình",
    priorityMax = "Tối đa",
    emptyScheduleTitle = "Chưa có kế hoạch nào hết",
)

internal val polishAppString = AppStrings(
    appName = "Time Planner",
    startTaskNotifyText = "Początek zdarzenia",
    timeTaskChannelName = "Wydarzenia",
    categoryWorkTitle = "Praca",
    categoryRestTitle = "Odpoczynek",
    categorySportTitle = "Sport",
    categorySleepTitle = "Sen",
    categoryCultureTitle = "Kultura",
    categoryChoresTitle = "Obowiązki",
    categoryTransportTitle = "Transport",
    categoryStudyTitle = "Nauka",
    categoryEatTitle = "Jedzenie",
    categoryEntertainmentsTitle = "Rozrywka",
    categoryHygieneTitle = "Higiena osobista",
    categoryHealthTitle = "Zdrowie",
    categoryShoppingTitle = "Zakupy",
    categoryOtherTitle = "Inne",
    minutesSymbol = "m",
    hoursSymbol = "h",
    separator = ":",
    cancelTitle = "Anuluj",
    confirmTitle = "Wybierz",
    okConfirmTitle = "OK",
    categoryEmptyTitle = "Brak",
    expandedViewToggleTitle = "Widok rozszerzony",
    compactViewToggleTitle = "Widok kompaktowy",
    warningDialogTitle = "Ostrzeżenie!",
    warningDeleteConfirmTitle = "Usuń",
    hoursTitle = "Godziny",
    minutesTitle = "Minuty",
    dayTitle = "Dni",
    homeTabTitle = "Strona główna",
    analyticsTabTitle = "Analizy",
    settingsTabTitle = "Ustawienia",
    mainDrawerTitle = "Planer",
    overviewDrawerTitle = "Przegląd",
    drawerMainSection = "Plany",
    templateDrawerTitle = "Szablony",
    categoriesDrawerTitle = "Kategorie",
    splitFormat = "%s | %s",
    amFormatTitle = "AM",
    pmFormatTitle = "PM",
    repeatTimeDayInWeekTitle = "Co tydzień",
    repeatTimeWeekDayInMonthTitle = "Co tydzień miesiąca",
    repeatTimeDayInMonthTitle = "Co miesiąc",
    repeatTimeDayInYearTitle = "Co rok",
    sundayTitle = "Niedziela",
    mondayTitle = "Poniedziałek",
    tuesdayTitle = "Wtorek",
    wednesdayTitle = "Środa",
    thursdayTitle = "Czwartek",
    fridayTitle = "Piątek",
    saturdayTitle = "Sobota",
    januaryTitle = "Styczeń",
    februaryTitle = "Luty",
    marchTitle = "Marzec",
    aprilTitle = "Kwiecień",
    mayTitle = "Maj",
    juneTitle = "Czerwiec",
    julyTitle = "Lipiec",
    augustTitle = "Sierpień",
    septemberTitle = "Wrzesień",
    octoberTitle = "Październik",
    novemberTitle = "Listopad",
    decemberTitle = "Grudzień",
    beforeTaskNotifyText = "Przypomnienie o nadchodzącym wydarzeniu",
    afterTaskNotifyText = "Koniec wydarzenia",
    priorityStandard = "Zwykły",
    priorityMedium = "Średni",
    priorityMax = "Max.",
    emptyScheduleTitle = "Nic jeszcze nie zaplanowano",
)

val LocalAppStrings = staticCompositionLocalOf<AppStrings> {
    error("Core Strings is not provided")
}

fun fetchAppStrings(language: AppLanguage) = when (language) {
    AppLanguage.EN -> englishAppString
    AppLanguage.RU -> russianAppString
    AppLanguage.DE -> germanAppString
    AppLanguage.ES -> spanishAppString
    AppLanguage.FA -> persianAppString
    AppLanguage.FR -> frenchAppString
    AppLanguage.PT_BR -> brazilianPortugueseAppString
    AppLanguage.TR -> turkishAppString
    AppLanguage.VN -> vietnameseAppString
    AppLanguage.PL -> polishAppString
}