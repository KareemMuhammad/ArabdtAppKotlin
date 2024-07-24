package com.example.arabdtappkotlin.ui.view.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Preview
@Composable
fun AttendanceWidget() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .fillMaxWidth()
            .padding(13.dp)
    ) {
        DateContainer(date = "2024-07-24")
        Spacer(modifier = Modifier.weight(1f))
        VerticalDivider(color = MaterialTheme.colorScheme.surfaceVariant)
        Spacer(modifier = Modifier.weight(1f))
        TimeContainer("Check In")
        Spacer(modifier = Modifier.weight(1f))
        VerticalDivider(color = MaterialTheme.colorScheme.surfaceVariant)
        Spacer(modifier = Modifier.weight(1f))
        TimeContainer("Check Out")
        Spacer(modifier = Modifier.weight(1f))
        VerticalDivider(color = MaterialTheme.colorScheme.surfaceVariant)
        Spacer(modifier = Modifier.weight(1f))
        TimeContainer("Duration")
    }
}

@Composable
fun DateContainer(date: String, modifier: Modifier = Modifier) {
    val formattedDate = formatDate(date) // Function to format the date

    Box(
        modifier = modifier
            .width(52.dp)
            .height(52.dp)
            .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = formattedDate,
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 15.sp),
            color = MaterialTheme.colorScheme.background,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun TimeContainer(title: String) {
    Box(
        modifier = Modifier
            .height(52.dp),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
            )
            Text(text = "2:00 PM", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

private fun formatDate(date: String): String {
    val formatter = SimpleDateFormat("dd MMM", Locale.getDefault()) // Example format
    val parsedDate = try {
        formatter.parse(date)
    } catch (e: Exception) {
        Date()
    }
    return formatter.format(parsedDate)
}
