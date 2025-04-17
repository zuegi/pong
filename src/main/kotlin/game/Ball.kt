package game

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import engine.Component
import engine.Transform

// game/Ball.kt
class Ball(
    private val windowWidth: Float,
    private val windowHeight: Float,
) : Component() {
    private var dx = 200f
    private var dy = 150f

    override fun update(deltaTime: Float) {
        val t = gameObject.getComponent<Transform>() ?: return
        t.x += dx * deltaTime
        t.y += dy * deltaTime

        if (t.y < 0 || t.y + t.height > windowHeight) dy *= -1
        if (t.x < 0 || t.x + t.width > windowWidth) {
            t.x = windowWidth / 2
            t.y = windowHeight / 2
        }
    }

    override fun render(drawScope: DrawScope) {
        val t = gameObject.getComponent<Transform>() ?: return
        drawScope.drawRect(Color.White, Offset(t.x, t.y), Size(t.width, t.height))
    }
}
