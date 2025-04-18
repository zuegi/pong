import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import engine.Scene

@Suppress("FunctionName")
@Composable
fun DebugPanel(
    x: Float,
    y: Float,
    width: Float,
    height: Float,
) {
    Box(
        modifier =
            Modifier
                .offset(x.dp, y.dp)
                .size(width.dp, height.dp)
                .background(Color.DarkGray), // Hintergrundfarbe explizit setzen
    ) {
        Text(
            text = getDebugInfo(),
            color = Color.White, // Textfarbe explizit setzen
            modifier = Modifier.padding(8.dp), // Abstand vom Rand
        )
    }
}

fun getDebugInfo(): String {
    val gameObjectsInfo =
        Scene.gameObjects.joinToString(separator = "\n") { gameObject ->
            "GameObject: ${gameObject.name}, Components: ${gameObject.components().size}"
        }
    return """
        |Debug Info:
        |FPS: ${1000 / game.GameConfig.frameTime} ms
        |Objects: ${Scene.gameObjects.size}
        |$gameObjectsInfo
        """.trimMargin()
}
