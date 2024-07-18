package com.example.arabdtappkotlin.view.helpers

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp


@SuppressLint("ModifierFactoryUnreferencedReceiver")
@Composable
fun Modifier.appTextFieldModifier() : Modifier {
    return Modifier
        .background(color = MaterialTheme.colorScheme.inverseOnSurface)
        .clip(RoundedCornerShape(8.dp))
        .fillMaxWidth()
}

@Composable
fun appTextFieldColors() : TextFieldColors {
    return OutlinedTextFieldDefaults.colors(
        focusedBorderColor = MaterialTheme.colorScheme.primary,
        unfocusedBorderColor = MaterialTheme.colorScheme.primary,
        disabledBorderColor = MaterialTheme.colorScheme.primary
    )
}