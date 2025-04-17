package ui

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.drawText
import androidx.compose.ui.unit.sp
import engine.Component
import engine.Scene
import engine.Transform
import game.GameConfig

class DebugPanel(
    private val textMeasurer: TextMeasurer,
) : Component() {
    override fun render(drawScope: DrawScope) {
        val transform = gameObject.getComponent<Transform>() ?: return

        // Hintergrund zeichnen
        drawScope.drawRect(
            color = Color.Gray,
            topLeft = Offset(transform.x, transform.y),
            size =
                androidx.compose.ui.geometry
                    .Size(transform.width, transform.height),
        )

        // Beispieltext vorbereiten
        val debugText =
            buildAnnotatedString {
                append("Debug Info:\n")
                append("FPS: ${GameConfig.frameTime}\n")
                append("Objects: ${Scene.gameObjects.size}")
                Scene.gameObjects.forEach { gameObject ->
                    append("- ${gameObject::javaClass.name}\n")
                }
            }

        // Text zeichnen
        val textLayoutResult =
            textMeasurer.measure(
                text = debugText,
                style = TextStyle(color = Color.White, fontSize = 16.sp),
            )

        drawScope.drawText(
            textLayoutResult = textLayoutResult,
            topLeft = Offset(transform.x + 10f, transform.y + 20f),
        )
    }
}
