package com.frank.firstwear.presentation.ui.detail

import android.media.AudioDeviceInfo
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text
import com.frank.firstwear.presentation.common.audioOutputAvailable
import com.frank.firstwear.presentation.data.EmotionRepository

@Composable
fun DetailScreen(emotionId: Int, navigateToAudioPlayerScreen: () -> Unit) {

    val context = LocalContext.current
    val hasBuiltInSpeaker = context.audioOutputAvailable(AudioDeviceInfo.TYPE_BUILTIN_SPEAKER)
    val hasPairedSpeaker = context.audioOutputAvailable(AudioDeviceInfo.TYPE_BLUETOOTH_A2DP)

    val title = EmotionRepository.listEmotions.firstOrNull { it.id == emotionId }?.title ?: ""

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(title)
        Spacer(modifier = Modifier.height(2.dp))
        if (hasPairedSpeaker || hasBuiltInSpeaker) {
            Text("Play audio with")
            Spacer(modifier = Modifier.height(2.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (hasBuiltInSpeaker) {
                    Button(onClick = {
                        navigateToAudioPlayerScreen()
                    }) {
                        Text(
                            "Built-in Speaker",
                            style = TextStyle(fontSize = 8.sp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
                if (hasPairedSpeaker) {
                    Button(onClick = {
                        navigateToAudioPlayerScreen()
                    }) {
                        Text(
                            "Bluetooth Speaker", style = TextStyle(fontSize = 8.sp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        } else {
            Button(onClick = { }) {
                Text("Connect a headset")
            }
        }
    }
}