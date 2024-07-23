package com.example.arabdtappkotlin.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.example.arabdtappkotlin.R

@Composable
fun MyAlertDialog(title: String, message: String) {
    val shouldShowDialog = remember { mutableStateOf(true) } // 1

    if (shouldShowDialog.value) { // 2
        AlertDialog( // 3
            onDismissRequest = { // 4
                shouldShowDialog.value = false
            },
            // 5
            title = { Text(text = title) },
            text = { Text(text = message) },
            confirmButton = { // 6
                Button(
                    onClick = {
                        shouldShowDialog.value = false
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.confirm),
                    )
                }
            }
        )
    }
}
