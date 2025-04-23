package game

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import engine.Component
import engine.Transform
import utils.logger

/*
    Die Ball Klasse ist verantwortlich für das Zeichnen des Balls
 */
class Ball : Component() {
    private val log = logger<Ball>()

    override fun render(drawScope: DrawScope) {
        log.debug("Try to Render ball")
        val transform = gameObject.getComponent<Transform>() ?: return
        log.debug("Rendering ball at x=${transform.x}, y=${transform.y}, width=${transform.width}, height=${transform.height}")
        drawScope.drawRect(
            Color.White,
            Offset(transform.x, transform.y),
            Size(transform.width, transform.height),
        )
    }
}
