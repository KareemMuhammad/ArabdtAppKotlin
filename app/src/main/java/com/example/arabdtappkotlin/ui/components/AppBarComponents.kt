package com.example.arabdtappkotlin.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.arabdtappkotlin.R
import com.example.arabdtappkotlin.ui.theme.primaryDark

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CustomAppBar(barHeight: Int, title: String? = null, content: @Composable (() -> Unit)? = null) {
//    val (topElement, bottomElement) = createRefs()
    ConstraintLayout {
        Box(
            modifier = Modifier
                .height(barHeight.dp)
                .clip(RoundedCornerShape(bottomStart = 25.dp, bottomEnd = 25.dp))
                .background(color = primaryDark)
        ) {
            Image(
                painter = painterResource(id = R.drawable.app_bar_bg),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(bottomStart = 25.dp, bottomEnd = 25.dp)),
                contentScale = ContentScale.FillBounds
            )
        }
        content?.invoke() ?:
        CustomAppBarTitle(title = title ?: "")
    }
}

@Composable
private fun CustomAppBarTitle(title: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, top = 16.dp, bottom = 16.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.surfaceVariant
        )
    }
}