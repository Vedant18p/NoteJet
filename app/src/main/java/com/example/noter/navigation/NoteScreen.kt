package com.example.noter.navigation


enum class NoteScreens {
    DisplayScreen,
    InputScreen,
    EditScreen,
    SettingScreen,
    PrivacyPolicy,
    TandC;

    companion object{
        fun fromRoute(route : String?) : NoteScreens
                = when(route?.substringBefore("/")){
            DisplayScreen.name -> DisplayScreen
            InputScreen.name -> InputScreen
            EditScreen.name -> EditScreen
            SettingScreen.name -> SettingScreen
            PrivacyPolicy.name -> PrivacyPolicy
            TandC.name -> TandC
            null -> DisplayScreen
            else -> throw IllegalAccessException("Route $route is not recognized")
        }
    }
}