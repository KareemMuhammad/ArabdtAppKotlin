package com.example.arabdtappkotlin.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.arabdtappkotlin.R
import com.example.arabdtappkotlin.utils.Constants
import com.example.arabdtappkotlin.utils.PreferencesManager

data class OnboardPage(
    val imageRes: Int, val title: String, val description: String
)

val onboardPagesList = listOf(
    OnboardPage(
        imageRes = R.drawable.on_boarding_1,
        title = "Welcome to Onboarding",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
    ), OnboardPage(
        imageRes = R.drawable.on_boarding_2,
        title = "Explore Exciting Features",
        description = "Praesent at semper est, nec consectetur justo."
    ), OnboardPage(
        imageRes = R.drawable.on_boarding_3,
        title = "Get Started Now",
        description = "In auctor ultrices turpis at blandit."
    )
)

@Composable
fun OnboardingScreen(navController: NavController) {
    val onboardPages = onboardPagesList

    val currentPage = remember { mutableStateOf(0) }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        OnBoardImageView(
            modifier = Modifier
                .fillMaxWidth(),
            imageRes = onboardPages[currentPage.value].imageRes
        )

        OnBoardDetails(
            modifier = Modifier
                .padding(16.dp),
            currentPage = onboardPages[currentPage.value]
        )

        TabSelector(
            onboardPages = onboardPages,
            currentPage = currentPage.value,
            modifier = Modifier
                .width(100.dp)
                .align(alignment = Alignment.CenterHorizontally)
        ) { index ->
            currentPage.value = index
        }

        OnBoardNavButton(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp),
            currentPage = currentPage.value,
            noOfPages = onboardPages.size
        ) {
            currentPage.value++
            if (currentPage.value == onboardPages.size) {
                PreferencesManager(context = context).saveBoolean(
                    key = PreferencesManager.ONBOARDING_KEY,
                    true
                )
                navController.navigate(Constants.LOGIN_SCREEN_KEY)
            }
        }
    }
}

@Composable
private fun OnBoardImageView(modifier: Modifier = Modifier, imageRes: Int) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Box(modifier = Modifier

            .align(Alignment.BottomCenter)
            .graphicsLayer {
                // Apply alpha to create the fading effect
                alpha = 0.6f
            }
            .background(
                Brush.verticalGradient(
                    colorStops = arrayOf(
                        Pair(0.8f, Color.Transparent), Pair(1f, Color.White)
                    )
                )
            ))
    }
}

@Composable
private fun OnBoardDetails(
    modifier: Modifier = Modifier, currentPage: OnboardPage
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = currentPage.title,
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = currentPage.description,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun OnBoardNavButton(
    modifier: Modifier = Modifier, currentPage: Int, noOfPages: Int, onNextClicked: () -> Unit
) {
    Button(
        onClick = {
            onNextClicked()
        }, modifier = modifier
    ) {
        Text(text = if (currentPage < noOfPages - 1) "Next" else "Get Started")
    }
}

@Composable
private fun TabSelector(
    modifier: Modifier = Modifier,
    onboardPages: List<OnboardPage>,
    currentPage: Int,
    onTabSelected: (Int) -> Unit,
) {
    TabRow(
        selectedTabIndex = currentPage,
        containerColor = Color.Transparent,
        modifier = modifier,
    ) {
        onboardPages.forEachIndexed { index, _ ->
            Tab(
                selected = index == currentPage,
                onClick = {
                    onTabSelected(index)
                },

                modifier = Modifier.padding(10.dp),
                content = {
                    Box(
                        modifier = Modifier
                            .size(4.dp)
                            .background(
                                color = if (index == currentPage) MaterialTheme.colorScheme.onPrimary
                                else Color.LightGray, shape = RoundedCornerShape(4.dp)
                            )
                    )
                }
            )
        }
    }
}