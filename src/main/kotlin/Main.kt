// Main.kt
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import engine.Scene
import game.GameConfig
import game.GameConfig.frameTime
import game.GameConfig.gameHeight
import game.GameConfig.windowWidth
import game.setupGame
import kotlinx.coroutines.delay
import java.awt.Dimension

// Main.kt
fun main() =
    application {
        val screenSize: Dimension =
            java.awt.Toolkit
                .getDefaultToolkit()
                .screenSize

        val x = (screenSize.width - windowWidth) / 2
        val y = (screenSize.height - gameHeight) / 2

        Window(
            onCloseRequest = ::exitApplication,
            title = "Pong in Kotlin",
            state =
                rememberWindowState(
                    width = windowWidth.dp,
                    height = gameHeight.dp,
                    position = WindowPosition(x.dp, y.dp),
                ),
        ) {
            val state = remember { mutableStateOf(0L) }
            val textMeasurer = rememberTextMeasurer() // TextMeasurer initialisieren

            // Setup only once
            LaunchedEffect(Unit) {
                setupGame(GameConfig.gameWidth, gameHeight, GameConfig.debugPanelWidth, textMeasurer)
                while (true) {
                    delay(frameTime)
                    Scene.update(frameTime / 1000f)
                    state.value++
                }
            }

            Canvas(modifier = Modifier.fillMaxSize().background(Color.Black)) {
                Scene.render(this)
            }
        }
    }
