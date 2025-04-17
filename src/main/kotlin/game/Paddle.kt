package game

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import engine.Component
import engine.Transform
import utils.Key
import utils.Keyboard

class Paddle(
    private val upKey: Key,
    private val downKey: Key,
) : Component() {
    override fun update(deltaTime: Float) {
        val t = gameObject.getComponent<Transform>() ?: return
        val speed = 300f
        if (Keyboard.isKeyPressed(upKey)) t.y -= speed * deltaTime
        if (Keyboard.isKeyPressed(downKey)) t.y += speed * deltaTime
    }

    override fun render(drawScope: DrawScope) {
        val t = gameObject.getComponent<Transform>() ?: return
        drawScope.drawRect(Color.White, Offset(t.x, t.y), Size(t.width, t.height))
    }
}
