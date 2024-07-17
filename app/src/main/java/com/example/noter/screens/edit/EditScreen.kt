package com.example.noter.screens.edit

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.noter.R
import com.example.noter.component.ShowConfirmationDialog
import com.example.noter.component.TextFieldTest
import com.example.noter.component.TextFieldTestTitle
import com.example.noter.model.Note
import com.example.noter.navigation.NoteScreens
import com.example.noter.screens.NoteViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.UUID


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreen(
    navController: NavController,
    uuidString: String,
    noteViewModel: NoteViewModel
) {
    var isAnyTextFieldFocused by remember { mutableStateOf(false) }
    val note = remember { mutableStateOf<Note?>(null) } // Store the note in a mutable state

    LaunchedEffect(key1 = uuidString) { // Launch a coroutine to update the note variable
        noteViewModel.getNoteById(uuidString).collect{fetchedNote ->
            note.value = fetchedNote
        }
    }




    val titleFocusRequester = remember { FocusRequester() }
    val descriptionFocusRequester= remember { FocusRequester() }
    Column(modifier = Modifier.fillMaxSize().systemBarsPadding() // Add this line
        .imePadding()){
        note.value?.let {note->
            var title by remember {
                mutableStateOf(note.title)
            }
            var description by remember {
                mutableStateOf(TextFieldValue(note.description))
            }
            var temp by remember {
                mutableStateOf(false)
            }

            BackHandler {
                if(description != TextFieldValue(note.description) || title != note.title){
                    temp = true
                }else{
                    navController.navigate(NoteScreens.DisplayScreen.name) {popUpTo(navController.graph.startDestinationId) {
                        inclusive = true
                    }
                    }
                }
            }
            if(temp){
                ShowConfirmationDialog {confirmed->

                    if (confirmed) {
                        // Handle confirmation (e.g., navigate back)
                        navController.navigate(NoteScreens.DisplayScreen.name) {popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        }
                    } else {
                        // Handle cancellation
                        temp = false
                    }
                }
            }

            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 0.dp, top = 0.dp, end = 15.dp, bottom = 0.dp),
                title = {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "back", modifier = Modifier.clickable {
                            navController.navigate(NoteScreens.DisplayScreen.name) {popUpTo(navController.graph.startDestinationId) {
                                inclusive = true
                            }
                            }
                        } )
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(text = stringResource(id = R.string.app_name), fontFamily = FontFamily.Monospace, fontStyle = FontStyle.Italic, fontWeight = FontWeight.Bold)
                    }
                },
                actions = {
                    if(isAnyTextFieldFocused){
                        Icon(imageVector = Icons.Filled.Done,
                            contentDescription = "Done",
                            modifier = Modifier.clickable {
                                val updatedNote = Note(id = UUID.fromString(uuidString), title = title, description = description.text)
                                noteViewModel.updateNote(updatedNote)
                                isAnyTextFieldFocused = false
                                navController.navigate(route = NoteScreens.EditScreen.name+"/${uuidString}")

                            }
                        )
                    }else{
                        Icon(imageVector = Icons.Filled.Delete,
                            contentDescription = "Delete",
                            modifier = Modifier.clickable {
                                noteViewModel.removeNote(uuidString)
                                navController.navigate(NoteScreens.DisplayScreen.name) {popUpTo(navController.graph.startDestinationId) {
                                    inclusive = true
                                }
                                }
                            }
                        )
                    }
                },
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp, 5.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ){

                TextFieldTestTitle(
                    value = title,
                    onValueChange = {
                        title = it
                    },
                    modifier = Modifier
                        .padding(1.dp, bottom = 7.4.dp)
                        .fillMaxWidth()
                        .focusRequester(titleFocusRequester)
                        .onFocusChanged {
                            isAnyTextFieldFocused = it.isFocused
                        },
                    textStyle = TextStyle(fontSize = 23.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.5.sp, lineHeight = 27.sp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = androidx.compose.ui.text.input.ImeAction.Next,
                        keyboardType = androidx.compose.ui.text.input.KeyboardType.Text
                    ),
                    keyboardActions = KeyboardActions(onNext = {
                        descriptionFocusRequester.requestFocus()
                        description = TextFieldValue(
                            text = description.text,
                            selection = TextRange(description.text.length)
                        )
                    }),
                    placeholder = "Title"
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 17.dp, bottom = 13.dp)
                ) {
                    Text(
                        text = LocalDateTime.now().format(
                            DateTimeFormatter.ofPattern(
                                "dd MMM hh:mm a",
                                Locale.getDefault()
                            )
                        ),
                        modifier = Modifier.padding(),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                    Text(
                        text = " | ${description.text.replace(" ","").length} characters",
                        modifier = Modifier.padding(),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                }
                TextFieldTest(
                    value = description,
                    onValueChange = {
                        description = it
                    },
                    modifier = Modifier
                        .padding(1.5.dp, 12.dp)
                        .fillMaxSize()
                        .focusRequester(descriptionFocusRequester)
                        .onFocusChanged {
                            isAnyTextFieldFocused = it.isFocused
                        },
                    textStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.W500, letterSpacing = 0.48.sp, lineHeight = 24.sp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = androidx.compose.ui.text.input.ImeAction.Next,
                        keyboardType = androidx.compose.ui.text.input.KeyboardType.Text
                    ),
                    keyboardActions = KeyboardActions(onNext = {
                        val newText = description.text + "\n"
                        description = TextFieldValue(
                            text = newText,
                            selection = TextRange(newText.length) // Set selection to the end
                        )

                    }
                    ),
                    placeholder = "Start Typing"

                )

            }
        }
    }
}