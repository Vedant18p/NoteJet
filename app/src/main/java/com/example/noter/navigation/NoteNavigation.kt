package com.example.noter.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.noter.screens.NoteViewModel
import com.example.noter.screens.display.DisplayScreen
import com.example.noter.screens.edit.EditScreen
import com.example.noter.screens.input.InputScreen
import com.example.noter.screens.settings.PrivacyPolicyScreen
import com.example.noter.screens.settings.SettingsScreen
import com.example.noter.screens.settings.TermsAndConditionsScreen

@Composable
fun NoteNavigation() {
    val navController = rememberNavController()
    val noteViewModel = viewModel<NoteViewModel>()
    //val noteList = noteViewModel.noteList.collectAsState().value

    NavHost(navController = navController, startDestination = NoteScreens.DisplayScreen.name) {

        composable(route = NoteScreens.DisplayScreen.name){
            DisplayScreen(navController,noteViewModel)
        }

        composable(NoteScreens.InputScreen.name){
            InputScreen(navController,noteViewModel)
        }


        composable(route = NoteScreens.EditScreen.name+"/{uuid}")
        {
            val uuid = it.arguments?.getString("uuid") ?: ""
            EditScreen(navController, uuid, noteViewModel)
        }
        composable(route = NoteScreens.SettingScreen.name){
            SettingsScreen(navController = navController,noteViewModel)
        }

        composable(NoteScreens.PrivacyPolicy.name){
            PrivacyPolicyScreen{
                navController.popBackStack()
            }
        }
        composable(NoteScreens.TandC.name){
            TermsAndConditionsScreen {
                navController.popBackStack()
            }
        }

    }
}