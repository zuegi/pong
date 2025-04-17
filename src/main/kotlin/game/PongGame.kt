package game

import androidx.compose.ui.text.TextMeasurer
import engine.GameObject
import engine.Scene
import engine.Transform
import game.GameConfig.gameWidth
import ui.DebugPanel
import utils.Key

fun setupGame(
    gameWitdth: Float,
    gameHeight: Float,
    debugPanelWidth: Float,
    textMeasurer: TextMeasurer,
) {
    val left =
        GameObject().apply {
            addComponent(Transform(50f, gameHeight / 2 - 50, 20f, 100f))
            addComponent(Paddle(Key.W, Key.S))
        }

    val right =
        GameObject().apply {
            addComponent(Transform(gameWitdth - 70f, gameHeight / 2 - 50, 20f, 100f))
            addComponent(Paddle(Key.DirectionUp, Key.DirectionDown))
        }

    val ball =
        GameObject().apply {
            addComponent(Transform(gameWitdth / 2, gameHeight / 2, 20f, 20f))
            addComponent(Ball(gameWitdth, gameHeight))
        }

    val debugPanel =
        GameObject().apply {
            // Positioniere das DebugPanel rechts neben dem Spielfeld
            addComponent(Transform(gameWidth, 10f, debugPanelWidth, gameHeight - 20f))
            addComponent(DebugPanel(textMeasurer))
        }

    Scene.gameObjects.addAll(listOf(left, right, ball, debugPanel))
}
