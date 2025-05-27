package com.deepakjetpackcompose.nosleep.util

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.deepakjetpackcompose.nosleep.model.Preference
import com.deepakjetpackcompose.nosleep.R
import com.deepakjetpackcompose.nosleep.ui.theme.text


@Composable
fun ChooseComposable(
    modifier: Modifier = Modifier,
    selectedKeywords: List<String>,
    onPreferenceClick: (String) -> Unit
) {
    val preference = listOf<Preference>(
        Preference(
            title = "Improve Performance without cup of coffee",
            img = R.drawable.bg2,
            personImg = R.drawable.girl_coffee,
            color = Color(0xFF7B87BA)
        ),
        Preference(
            title = "Reduce Stress",
            img = R.drawable.bg3,
            personImg = R.drawable.stres,
            color = Color(0xFFF2DCC2)
        ),
        Preference(
            title = "Better Sleep",
            img = R.drawable.bg2,
            personImg = R.drawable.sleep,
            color = Color(0xFFFA9490)
        ),
        Preference(
            title = "Increase Happiness",
            img = R.drawable.bg2,
            personImg = R.drawable.happiness,
            color = Color(0xFF404756)
        ),
        Preference(
            title = "Achieve your goals",
            img = R.drawable.bg3,
            personImg = R.drawable.goals,
            color = Color(0xFFF2DCC2)
        ),
        Preference(
            title = "Listening to the soothing music",
            img = R.drawable.bg2,
            personImg = R.drawable.musicboy,
            color = Color(0xFFCED8F2)
        )
    )
    val bottomPadding = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .navigationBarsPadding(),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalItemSpacing = 24.dp,
        contentPadding = PaddingValues(bottom = bottomPadding + 32.dp)
    ) {
        items(preference) { item ->
            ChooseContent(
                textTitle = item.title,
                img = item.img,
                personImg = item.personImg,
                bgColor = item.color,
                isClicked = selectedKeywords.contains(item.title),
                onClick = { onPreferenceClick(item.title)
                }
            )
        }
    }

}