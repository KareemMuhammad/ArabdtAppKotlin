package com.example.arabdtappkotlin.ui.view.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.arabdtappkotlin.R
import com.example.arabdtappkotlin.ui.navigation.Routes
import com.example.arabdtappkotlin.viewModel.UserViewModel

data class BottomNavItem(
    val iconActive: Int,
    val iconDisabled: Int,
    val label: String,
    val route: String,
    val content: @Composable () -> Unit
)

@Composable
fun MainScreen(navController: NavController, userViewModel: UserViewModel) {
    val pages = listOf(
        BottomNavItem(R.drawable.ic_home_bottom_bar_active,
            R.drawable.ic_home_bottom_bar_active,
            "Home",
            Routes.HOME_SCREEN_KEY,
            content = { HomeScreen(navController, userViewModel) }),
        BottomNavItem(R.drawable.ic_more_bottom_bar_active,
            R.drawable.ic_more_bottom_bar_disabled,
            "More",
            Routes.MORE_SCREEN_KEY,
            content = { MoreScreen() })
    )
    var pageIndex by remember { mutableIntStateOf(0) }
    Scaffold(bottomBar = {
        Surface(shadowElevation = 10.dp) {
            NavigationBar(
                modifier = Modifier.height(70.dp),
                containerColor = Color.Transparent,
                tonalElevation = 0.dp
            ) {
                pages.forEachIndexed { index, item ->
                    NavigationBarItem(icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = if (pageIndex == index) item.iconActive else item.iconDisabled),
                            contentDescription = item.label,
                            tint = Color.Unspecified,
                        )
                    }, selected = pageIndex == index, onClick = {
                        pageIndex = index
                    }, label = {
                        Text(
                            text = item.label,
                            color = if (pageIndex == index) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
                        )
                    })
                }
            }
        }

    }) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            pages[pageIndex].content.invoke()
        }
    }
}
