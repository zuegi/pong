package ui

import DebugPanel
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import engine.Scene
import game.GameConfig.debugPanelWidth
import game.GameConfig.gameHeight
import game.GameConfig.gameWidth

// Globale Variable für die Zeitmessung
private var lastTime = System.nanoTime()

@Suppress("FunctionName")
@Composable
fun GameCanvas() {
    Canvas(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        val deltaTime = calculateDeltaTime()
        Scene.update(deltaTime)
        Scene.render(this)
    }
    // DebugPanel direkt rendern
    DebugPanel(
        x = gameWidth,
        y = 10f,
        width = debugPanelWidth,
        height = gameHeight,
    )
}

fun calculateDeltaTime(): Float {
    val currentTime = System.nanoTime()
    val deltaTime = (currentTime - lastTime) / 1_000_000_000f // Umrechnung in Sekunden
    lastTime = currentTime
    return deltaTime
}
