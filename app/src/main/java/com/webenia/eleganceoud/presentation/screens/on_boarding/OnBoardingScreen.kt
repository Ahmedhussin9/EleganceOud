package com.webenia.eleganceoud.presentation.screens.on_boarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.webenia.eleganceoud.R
import com.webenia.eleganceoud.ui.theme.Primary
import kotlinx.coroutines.launch

data class OnBoardingPage(val title: String, val description: String, val image: Int)


@Composable
fun OnBoardingScreenSetup(
    onDone: () -> Unit,

    ) {
    val context = LocalContext.current

    val onboardingPages = listOf(
        OnBoardingPage(
            title = context.getString(R.string.onboradingfirst), // example
            description = context.getString(R.string.subonboardingtext),
            image = R.drawable.img_onboradigfirst
        ),
        OnBoardingPage(
            title = context.getString(R.string.onboradingsecond),
            description = context.getString(R.string.subonboardingtext),
            image = R.drawable.img_onboradingsecond
        ),
        OnBoardingPage(
            title = context.getString(R.string.onboradingthird),
            description = context.getString(R.string.subonboardingtext),
            image = R.drawable.img_onboradingthird
        )
    )
    OnBoardingScreenContent(onDone = onDone, onboardingPages = onboardingPages)
}

@Composable
fun OnBoardingScreenContent(
    onDone: () -> Unit = {},
    onboardingPages: List<OnBoardingPage>,
) {
    val pagerState = rememberPagerState(pageCount = { onboardingPages.size })
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {
            if (pagerState.currentPage < onboardingPages.lastIndex) {
                TextButton(onClick = { onDone() }) {
                    Text("Skip", color = Primary, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                }
            } else {
                Spacer(modifier = Modifier.width(64.dp))
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            val item = onboardingPages[page]
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = item.image),
                    contentDescription = item.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(450.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        item.title, style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        item.description,
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 12.sp,
                    )
                }
            }
        }

        PagerDotsIndicator(
            totalDots = onboardingPages.size,
            selectedIndex = pagerState.currentPage,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                modifier = Modifier.fillMaxWidth().clip(
                    RoundedCornerShape(5.dp)
                ),
                onClick = {
                    if (pagerState.currentPage == onboardingPages.lastIndex) {
                        onDone()
                    } else {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                }
            ) {
                Text(
                    if (pagerState.currentPage == onboardingPages.lastIndex) "Done" else "Next",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun PagerDotsIndicator(
    totalDots: Int,
    selectedIndex: Int,
    modifier: Modifier = Modifier,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unSelectedColor: Color = Color.LightGray
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        repeat(totalDots) { index ->
            val color = if (index == selectedIndex) selectedColor else unSelectedColor
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(if (index == selectedIndex) 12.dp else 8.dp)
                    .clip(CircleShape)
                    .background(color)
            )
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun OnBoardingScreenPreview() {
    OnBoardingScreenContent(
        onboardingPages = listOf(
            OnBoardingPage(
                title = "Title 1",
                description = "Description 1",
                image = R.drawable.img_onboradigfirst
            ),
        )
    )
}