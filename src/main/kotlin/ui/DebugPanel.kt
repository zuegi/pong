import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import engine.Scene
import engine.Transform
import ui.BallBehavior

@Suppress("FunctionName")
@Composable
fun DebugPanel(
    x: Float,
    y: Float,
    width: Float,
    height: Float,
    frameTrigger: Long,
) {
    // Finden des Balls in der Scene
    val ball = Scene.gameObjects.find { it.name == "Ball" }
    val transform = ball?.getComponent<Transform>()
    val ballBehavior = ball?.getComponent<BallBehavior>()

    val speedLength =
        if (ballBehavior != null) {
            Math.sqrt((ballBehavior.dx * ballBehavior.dx + ballBehavior.dy * ballBehavior.dy).toDouble()).toFloat()
        } else {
            0f
        }

    Box(
        modifier =
            Modifier
                .offset(x.dp, y.dp)
                .size(width.dp, height.dp)
                .background(Color.DarkGray),
    ) {
        // Zeichne die Geschwindigkeitslinie, wenn der Ball existiert
        if (transform != null && ballBehavior != null) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                // Linie wird basierend auf der Geschwindigkeit des Balls gezeichnet
                drawLine(
                    color = Color.Green, // Farbe der Linie
                    start = Offset(transform.x, transform.y),
                    end = Offset(transform.x + ballBehavior.dx * 20f, transform.y + ballBehavior.dy * 20f),
                    strokeWidth = 4f, // Dicke der Linie
                )
            }
        }

        // Text mit Debug-Infos
        Text(
            text = getDebugInfo(),
            color = Color.White,
            modifier = Modifier.padding(8.dp),
        )
    }
}

fun getDebugInfo(): String {
    val ball = Scene.gameObjects.find { it.name == "Ball" }
    val transform = ball?.getComponent<Transform>()

    val ballInfo =
        if (transform != null) {
            "Ball Position: x=${"%.2f".format(transform.x)}, y=${"%.2f".format(transform.y)}"
        } else {
            "Ball Position: unavailable"
        }

    val gameObjectsInfo =
        Scene.gameObjects.joinToString(separator = "\n") { gameObject ->
            "GameObject: ${gameObject.name}, Components: ${gameObject.components().size}"
        }
    return """
        |Debug Info:
        |FPS: ${1000 / game.GameConfig.frameTime} ms
        |Objects: ${Scene.gameObjects.size}
        |$gameObjectsInfo
        |
        |Ball Info: $ballInfo
        """.trimMargin()
}
