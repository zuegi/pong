package ui

import androidx.compose.ui.window.WindowPosition.PlatformDefault.x
import androidx.compose.ui.window.WindowPosition.PlatformDefault.y
import engine.Component
import engine.Transform
import game.GameConfig

/*
    Die BallBehavior Klasse ist verantwortlich für die Bewegung, Steuerung und Kollisionen.
 */
class BallBehavior : Component() {
    var dx = 0f
    var dy = 0f
    val speed = 250f

    override fun update(deltaTime: Float) {
        if (deltaTime <= 0) return // Keine Berechnungen bei ungültigem deltaTime

        val transform = gameObject.getComponent<Transform>() ?: return

        // Bewegung des Balls
        if (dx != 0f || dy != 0f) {
            transform.x += dx * speed * deltaTime
            transform.y += dy * speed * deltaTime
            println("Ball Position: x=${transform.x}, y=${transform.y}")
        }

        // Reflexion oben/unten
        if (transform.y < 0 || transform.y > GameConfig.gameHeight - transform.height) {
            dy *= -1
        }

        // Zurücksetzen, wenn der Ball das Spielfeld verlässt
        if (transform.x < 0 || transform.x > GameConfig.gameWidth) {
            resetBall(transform)
        }
    }

    private fun resetBall(transform: Transform) {
        transform.x = GameConfig.gameWidth / 2 - transform.width / 2
        transform.y = GameConfig.gameHeight / 2 - transform.height / 2
        dx = if (dx > 0) -1f else 1f // Richtung umkehren
        dy = 1f
    }

    fun startBall() {
        println("Starting Ball...")
        if (dx == 0f && dy == 0f) { // Nur starten, wenn der Ball ruht
            dx = if ((0..1).random() == 0) 1f else -1f // Zufällige Richtung
            dy = if ((0..1).random() == 0) 1f else -1f
            println("Ball gestartet: dx=$dx, dy=$dy")
        } else {
            println("Ball nicht gestartet: dx=$dx, dy=$dy")
        }
    }

    fun onSpacePressed() {
        println("Leertaste wurde gedrückt. Ball wird gestartet.")
        startBall()
    }
}
