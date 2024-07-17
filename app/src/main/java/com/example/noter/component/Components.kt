package com.example.noter.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//
//@Composable
//fun InputTextField(
//    modifier: Modifier = Modifier,
//    label : String,
//    text : String,
//    maxLines : Int,
//    onTextChange : (String) -> Unit,
//    onImeAction : () -> Unit = {},
//    fontStyle: TextStyle = TextStyle(),
//    placeholder: String = ""
//
//){
//    val keyboardController = LocalSoftwareKeyboardController.current
//    TextField(
//        value = text,
//        onValueChange = onTextChange,
//        maxLines = maxLines,
//        label = { Text(text = label, fontSize = fontStyle.fontSize, fontWeight = FontWeight.Bold, color = Color.Gray)},
//        keyboardOptions = KeyboardOptions.Default.copy(
//            imeAction = ImeAction.Done
//        ),
//        keyboardActions = KeyboardActions(onDone = {
//            onImeAction()
//            keyboardController?.hide()
//        }),
//        modifier = modifier,
//        placeholder = { Text(text = "$placeholder")},
//        colors = TextFieldDefaults.colors(
//            focusedContainerColor = Color.Transparent,
//            unfocusedContainerColor = Color.Transparent,
//        ),
//        textStyle = fontStyle
//    )
//}


@Composable
fun ExtendedFloatButton(onClick: () -> Unit, text : String, modifier: Modifier){
    ExtendedFloatingActionButton(modifier = modifier, text = { Text(text) }, icon = { Icon(Icons.Filled.Create,"Create") }, onClick =  onClick)
}

@Composable
fun InputTextFieldNew(
    modifier: Modifier = Modifier,
    label: String,
    text: String,
    maxLines: Int = Int.MAX_VALUE, // Allow unlimited lines by default
    onTextChange: (String) -> Unit,
    onImeAction: () -> Unit = {},
    fontStyle: TextStyle = TextStyle(),
    placeholder: String = "",
    leadingIcon: (@Composable () -> Unit)? = null // Add a leading icon slot
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        value = text,
        onValueChange = onTextChange,
        maxLines = maxLines,
        label = {
            Text(
                text = label,
                fontSize = fontStyle.fontSize,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Text // Set keyboard type to text
        ),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyboardController?.hide()
        }),
        modifier = modifier
            .fillMaxWidth() // Make the text field fill the width
            .padding(8.dp), // Add some padding

        placeholder = { Text(text = placeholder,fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray ) },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent, // Remove the indicator line
            unfocusedIndicatorColor = Color.Transparent
        ),
        textStyle = fontStyle,
        leadingIcon = leadingIcon // Display the leading icon if provided
    )
}

@Preview(showBackground = true)
@Composable
private fun NewInputPreview() {
    InputTextFieldNew(label = "Description", text = "", onTextChange = {})
}





@Composable
fun TextFieldTestTitle(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    maxLines: Int = Int.MAX_VALUE,
    placeholder: String
){
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        textStyle = textStyle,
        label = label,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        maxLines = maxLines,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent, // Remove the indicator line
            unfocusedIndicatorColor = Color.Transparent
        ),
        placeholder = { Text(text = placeholder,fontSize = textStyle.fontSize,
            fontWeight = FontWeight.Bold,
            color = Color.Gray) }
    )
}

@Composable
fun TextFieldTest(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    maxLines: Int = Int.MAX_VALUE,
    placeholder: String
){
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        textStyle = textStyle,
        label = label,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        maxLines = maxLines,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent, // Remove the indicator line
            unfocusedIndicatorColor = Color.Transparent
        ),
        placeholder = { Text(text = placeholder,fontSize = textStyle.fontSize,
            fontWeight = FontWeight.Bold,
            color = Color.Gray) }
    )
}

@Composable
fun ShowConfirmationDialog(onConfirm: (Boolean) -> Unit) {
    var showDialog by remember { mutableStateOf(true) }

    if (showDialog) {
        AlertDialog(onDismissRequest = {
            showDialog = false
                                       onConfirm(false)},
            title = { Text("Confirm Navigation") },
            text = { Text("Are you sure you want to go back? Unsaved changes will be lost.") },
            confirmButton = {
                Button(onClick = {
                    showDialog = false
                    onConfirm(true) // User confirmed
                }) {
                    Text("Yes")
                }
            },
            dismissButton = {
                Button(onClick = {
                    showDialog = false
                    onConfirm(false) // User canceled
                }) {
                    Text("No")
                }
            }
        )
    }
}

@Composable
fun DeleteAllConfirm(onConfirm: (Boolean) -> Unit) {
    var showDialog by remember { mutableStateOf(true) }

    if (showDialog) {
        AlertDialog(onDismissRequest = {
            showDialog = false
            onConfirm(false)},
            title = { Text("Confirm Deletion") },
            text = { Text("Are you sure you want to delete all notes? This step can not be reversed") },
            confirmButton = {
                Button(onClick = {
                    showDialog = false
                    onConfirm(true) // User confirmed
                }) {
                    Text("Yes")
                }
            },
            dismissButton = {
                Button(onClick = {
                    showDialog = false
                    onConfirm(false) // User canceled
                }) {
                    Text("No")
                }
            }
        )
    }
}


@Composable
fun SettingItem(text: String, onClick: () -> Unit) {
    Text(
        text = text,
        fontWeight = FontWeight.Medium,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 15.dp, horizontal = 10.dp)
    )
    HorizontalDivider()
}