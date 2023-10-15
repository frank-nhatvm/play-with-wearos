/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.frank.firstwear.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.media3.common.util.UnstableApi
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.frank.firstwear.R
import com.frank.firstwear.presentation.theme.FirstWearTheme
import com.frank.firstwear.presentation.ui.audio.AudioScreen
import com.frank.firstwear.presentation.ui.detail.DetailScreen
import com.frank.firstwear.presentation.ui.home.HomeScreen

class MainActivity : ComponentActivity() {
    @UnstableApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WearApp()
        }
    }
}

@Composable
@UnstableApi
fun WearApp() {
    FirstWearTheme {
        /* If you have enough items in your list, use [ScalingLazyColumn] which is an optimized
         * version of LazyColumn for wear devices with some added features. For more information,
         * see d.android.com/wear/compose.
         */
        val navController = rememberSwipeDismissableNavController()
        SwipeDismissableNavHost(
            navController = navController,
            startDestination = "home"
        ) {
            composable(route = "home") {
                HomeScreen(onNavigateDetailScreen = { id ->
                    navController.navigate("detail/id=$id")
                })
            }

            composable(route = "detail/id={id}", arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                    nullable = false
                }
            )) {
                val id = it.arguments?.getInt("id") ?: 0
                DetailScreen(id) {
                    navController.navigate("audio_player")
                }
            }

            composable(route = "audio_player") {
                AudioScreen() {
                    navController.popBackStack()
                }
            }
        }
    }
}
