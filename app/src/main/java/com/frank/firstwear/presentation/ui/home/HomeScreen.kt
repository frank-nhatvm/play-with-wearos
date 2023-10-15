package com.frank.firstwear.presentation.ui.home

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.AutoCenteringParams
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.foundation.lazy.rememberScalingLazyListState
import androidx.wear.compose.material.Text
import com.frank.firstwear.presentation.data.EmotionEntity
import com.frank.firstwear.presentation.data.EmotionRepository
import com.frank.firstwear.presentation.theme.FirstWearTheme

@Composable
fun HomeScreen(onNavigateDetailScreen: (Int) -> Unit) {

    val listEmotions = EmotionRepository.listEmotions
    val listState = rememberScalingLazyListState()
    ScalingLazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize(),
        autoCentering = AutoCenteringParams(itemIndex = 0),

    ){
        items(listEmotions){
            emotion ->
            EmotionItem(emotionEntity = emotion, onSelected = {
                onNavigateDetailScreen(it)
            })
        }
    }

}


@Composable
private fun EmotionItem(modifier: Modifier = Modifier, emotionEntity: EmotionEntity, onSelected:(Int) -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.clickable(
            onClick = { onSelected(emotionEntity.id) }
        )
    ) {
        Text(text = emotionEntity.emoj,)
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = emotionEntity.title)
    }
}


@Preview(device = Devices.WEAR_OS_SMALL_ROUND, uiMode = Configuration.UI_MODE_TYPE_WATCH)
@Composable
fun EmotionItemPreview() {
    FirstWearTheme {
        EmotionItem(
            emotionEntity = EmotionEntity(
                id = 1,
                emoj = "\uD83D\uDE0C",
                title = "Calm"
            )
        ){}
    }
}