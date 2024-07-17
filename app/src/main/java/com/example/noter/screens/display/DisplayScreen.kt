package com.example.noter.screens.display

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.noter.component.ExtendedFloatButton
import com.example.noter.model.Note
import com.example.noter.navigation.NoteScreens
import com.example.noter.screens.NoteViewModel
import com.example.noter.util.formatDate


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayScreen(
    navController: NavController,
    noteViewModel: NoteViewModel,
) {
    val list = noteViewModel.noteList.collectAsState().value

    Column(modifier = Modifier.fillMaxSize()){
        TopAppBar(title = {
            Text(
                text = "NoteIt",
                fontFamily = FontFamily.Monospace,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
            )
        },
            actions = {
                IconButton(onClick = {
                    navController.navigate(route = NoteScreens.SettingScreen.name)
                }) {
                    Icon(Icons.Default.Settings, contentDescription = "Sort")
                }
            }
        )
        Scaffold(
            floatingActionButton = {
                ExtendedFloatButton(
                    onClick = {
                        navController.navigate(NoteScreens.InputScreen.name)
                    },
                    text = "Create",
                    modifier = Modifier.padding(16.dp)
                )
            }
        ){
            if(list.isEmpty()){
                Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                    .padding(it)
                    .fillMaxSize()){
                    Text(text = "Nothing to show here", fontWeight = FontWeight.Light )
                    Text(text = "Click \"Create\" to add a note", fontWeight = FontWeight.Light)
                }
            }else{
                LazyColumn(modifier = Modifier.padding(it)){
                    items(list,key = {note -> note.id.toString()}) { note ->
                        NoteRow(
                            note = note,
                            onClick = {
                                navController.navigate(route = NoteScreens.EditScreen.name+"/${note.id}")
                            }

                        )
                    }
                }
            }

        }
    }
}

@SuppressLint("SimpleDateFormat")
@Composable
fun NoteRow(
    note : Note,
    onClick : (Note) -> Unit,) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .border(1.dp, shape = RoundedCornerShape(12.dp), color = Color.DarkGray)
            .clickable {
                onClick(note)
            }
    ){
        var viewAll by remember {
            mutableStateOf(false)
        }
        var timeView by remember {
            mutableStateOf(false)
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, top = 12.dp, end = 15.dp, bottom = 5.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
            Text(
                text = note.title,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                modifier = Modifier
                    .clickable {
                        onClick(note)
                    },
                fontSize = 19.sp

            )
        }
        Text(text = note.description.ifEmpty {
            "No Text"
        },
            fontWeight = FontWeight.W400,
            maxLines = if(!viewAll) 1 else 5,modifier = Modifier
                .padding(start = 15.dp, top = 5.dp, end = 15.dp, bottom = 5.dp)
                .animateContentSize(animationSpec = tween(durationMillis = 300))
        )
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp, 0.dp)
            .padding(start = 0.dp, top = 5.dp, end = 10.dp, bottom = 10.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
            IconButton(onClick = { viewAll = !viewAll }, modifier = Modifier.size(22.dp)) {
                if(!viewAll){
                    Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "Expand")
                }else{
                    Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "Collapse")
                }
            }
            Text(text = formatDate(note.date.time),
                fontWeight = FontWeight.W300,
                maxLines = 1,
                modifier = Modifier
                    .clickable { timeView = !timeView },
                fontSize = 13.sp
            )
        }
    }
}