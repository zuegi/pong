// Main.kt
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import androidx.compose.runtime.remember
import engine.Scene
import game.GameConfig
import game.GameConfig.frameTime
import game.GameConfig.gameHeight
import game.GameConfig.windowWidth
import game.setupGame
import kotlinx.coroutines.delay
import ui.GameCanvas
import java.awt.Dimension

val frameTrigger = mutableStateOf(0L)


// Main.kt
fun main() =
    application {
        val screenSize: Dimension =
            java.awt.Toolkit
                .getDefaultToolkit()
                .screenSize

        val x = (screenSize.width - windowWidth) / 2
        val y = (screenSize.height - gameHeight) / 2ö

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
            val gameState = remember { mutableStateOf("start") }
            when (gameState.value) {
                "start" -> {
                    // Hier zeigst du später den Startbildschirm
                    // z. B. StartScreen(onModeSelected = { gameState.value = "playing" })
                }

                "playing" -> {
                    // Starte Spiel beim ersten Mal
                    LaunchedEffect(Unit) {
                        setupGame(GameConfig.gameWidth, gameHeight)
                        while (true) {
                            delay(frameTime)
                            Scene.update(1 / 60f)
                            frameTrigger.value++
                        }
                    }
                    GameCanvas(frameTrigger = frameTrigger.value)
                }

                "gameover" -> {
                    // Hier kommt später EndScreen()
                }
            }
            // Rendering ball at x=500.0, y=400.0
            // 👇 Das ist der wichtige Teil!
            frameTrigger.value
            // Spielschleife
            LaunchedEffect(Unit) {
                setupGame(GameConfig.gameWidth, gameHeight)
                while (true) {
                    delay(frameTime)
                    Scene.update(1 / 60f)
                    frameTrigger.value++ // Trigger für Recomposition
                }
            }
            // Canvas wird durch Recomposition aktualisiert
            GameCanvas(frameTrigger = frameTrigger.value)
        }
    }
