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
    private val moveSpeed = 300f

    // Für das Wobbeln
    private var wobbleVelocity = 0f // aktuelle Wobbelgeschwindigkeit
    private var wobbleOffset = 0f // wie weit ausgelenkt
    private val bounceStrength = 300f // Anfangsimpuls beim Aufprall
    private val damping = 5f // Dämpfung (je höher, desto schneller beruhigt es sich)

    override fun update(deltaTime: Float) {
        val transform = gameObject.getComponent<Transform>() ?: return

        // Normale Steuerung
        if (Keyboard.isKeyPressed(upKey)) {
            transform.y -= moveSpeed * deltaTime
        }
        if (Keyboard.isKeyPressed(downKey)) {
            transform.y += moveSpeed * deltaTime
        }

        // Spielfeldgrenzen prüfen und Bounce initialisieren
        if (transform.y < 0f) {
            transform.y = 0f
            wobbleVelocity = bounceStrength
        } else if (transform.y + transform.height > GameConfig.gameHeight) {
            transform.y = GameConfig.gameHeight - transform.height
            wobbleVelocity = -bounceStrength
        }

        // Wobble-Physik anwenden
        wobbleOffset += wobbleVelocity * deltaTime
        wobbleVelocity -= wobbleOffset * damping * deltaTime

        // Auf Paddle anwenden (optische Verschiebung)
        transform.y += wobbleOffset * deltaTime
    }

    override fun render(drawScope: DrawScope) {
        val transform = gameObject.getComponent<Transform>() ?: return
        drawScope.drawRect(
            Color.White,
            Offset(transform.x, transform.y),
            Size(transform.width, transform.height),
        )
    }
}
