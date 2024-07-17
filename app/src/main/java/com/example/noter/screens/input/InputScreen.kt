package com.example.noter.screens.input

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputScreen(
    navController: NavController,
    noteViewModel: NoteViewModel,
) {
    var title by remember {
        mutableStateOf(
            ""
        )
    }
    var description by remember { mutableStateOf(TextFieldValue("")) }
    val titleFocusRequester = remember { FocusRequester() }
    val descriptionFocusRequester= remember { FocusRequester() }


    var temp by remember {
        mutableStateOf(false)
    }

    BackHandler {
        if(description != TextFieldValue("") || title != ""){
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

    Column(modifier = Modifier.fillMaxSize()){
        TopAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 0.dp, top = 0.dp, end = 15.dp, bottom = 0.dp),
            title = {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "back",
                        modifier = Modifier.clickable {
                            if(description != TextFieldValue("") || title != ""){
                                temp = true
                            }else{
                                navController.navigate(NoteScreens.DisplayScreen.name) {popUpTo(navController.graph.startDestinationId) {
                                    inclusive = true
                                }
                                }
                            }
                        }
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(text = stringResource(id = R.string.app_name), fontFamily = FontFamily.Monospace, fontStyle = FontStyle.Italic, fontWeight = FontWeight.Bold)
                }
            },
            actions = {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "Done",
                    modifier = Modifier
                        .clickable {
                            if(description.text.isNotEmpty()){
                                //add note
                                if(title == "")title = "Title"
                                val note = Note(title = title, description = description.text)
                                noteViewModel.addNote(note)
                                navController.navigate(route = NoteScreens.EditScreen.name+"/${note.id}")
                            }
                        }
                )
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
                    .focusRequester(titleFocusRequester),
                textStyle = TextStyle(fontSize = 23.sp, fontWeight = FontWeight.Bold,letterSpacing = 0.5.sp, lineHeight = 27.sp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = androidx.compose.ui.text.input.ImeAction.Next,
                    keyboardType = androidx.compose.ui.text.input.KeyboardType.Text
                ),
                keyboardActions = KeyboardActions(onNext = {
                    descriptionFocusRequester.requestFocus()
                }),
                placeholder = "Title"
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 17.dp, bottom = 13.dp)
            ){
                Text(text = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMMM hh:mm a",
                    Locale.getDefault())),
                    modifier = Modifier.padding(),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
                Text(text = " | ${description.text.replace(" ","").length} characters",
                    modifier = Modifier,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
            }
            TextFieldTest(
                value = description,
                onValueChange = {newValue ->
                    description = newValue
                },
                modifier = Modifier
                    .padding(1.5.dp, 12.dp)
                    .fillMaxSize()
                    .focusRequester(descriptionFocusRequester)
                    .padding(bottom = WindowInsets.ime.asPaddingValues().calculateBottomPadding()),
                textStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.W500, letterSpacing = 0.48.sp, lineHeight = 24.sp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = androidx.compose.ui.text.input.ImeAction.Next,
                    keyboardType = androidx.compose.ui.text.input.KeyboardType.Text
                ),
                keyboardActions = KeyboardActions(onNext = {
                    val newText = description.text + "\n"
                    description = TextFieldValue(
                        text = newText,
                        selection = TextRange(newText.length)
                    )
                }
                ),
                placeholder = "Start Typing"

            )

        }
    }
}