package com.frank.firstwear.presentation.ui.audio

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.RawResourceDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import com.frank.firstwear.R

@Composable
@UnstableApi
fun AudioScreen(onBack: () -> Unit) {
    val videoPlayer = ExoPlayer.Builder(LocalContext.current).build()
    videoPlayer.repeatMode = Player.REPEAT_MODE_ALL
    videoPlayer.playWhenReady = true
    videoPlayer.prepare()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Playing Audio")
        Button(onClick = { onBack() }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_stop_circle),
                contentDescription = "stop"
            )
        }
    }

    val uri = RawResourceDataSource.buildRawResourceUri(R.raw.audio)
    val mediaItem = MediaItem.fromUri(uri)
    videoPlayer.setMediaItem(mediaItem)
    SideEffect {
        videoPlayer.play()
    }

    DisposableEffect(key1 = Unit) {
        onDispose {
            videoPlayer.stop()
            videoPlayer.release()
        }
    }
}

@UnstableApi
@Composable
fun RebelGirlsPlayer(
    modifier: Modifier = Modifier,
    player: Player
) {
    AndroidView(
        factory = { context ->
            PlayerView(context).also {
                it.player = player
                it.useController = true
                it.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
            }
        },
        modifier = modifier
    )
}