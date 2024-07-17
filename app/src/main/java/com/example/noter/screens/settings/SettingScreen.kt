package com.example.noter.screens.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.noter.component.DeleteAllConfirm
import com.example.noter.component.SettingItem
import com.example.noter.component.ShowConfirmationDialog
import com.example.noter.navigation.NoteScreens
import com.example.noter.screens.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController,noteViewModel: NoteViewModel) {
    var temp by remember {
        mutableStateOf(false)
    }

    if(temp){
        DeleteAllConfirm {confirmed->

            if (confirmed) {
                // Handle confirmation (e.g., navigate back)
                noteViewModel.deleteAllNotes()
            } else {
                // Handle cancellation
                temp = false
            }
        }
    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back" ,)
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(10.dp)
        ) {
            HorizontalDivider()
            SettingItem(text = "Delete All Notes") {
                temp = true
            }
            SettingItem(text = "Terms and Conditions") {
                navController.navigate(NoteScreens.TandC.name)
            }
            SettingItem(text = "Privacy Policy") {
                navController.navigate(NoteScreens.PrivacyPolicy.name)
            }

            // Add more setting items as needed
        }
    }
}
