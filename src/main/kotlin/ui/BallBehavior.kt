package ui

import engine.Component
import engine.Transform
import game.GameConfig
import utils.logger

/*
    Die BallBehavior Klasse ist verantwortlich für die Bewegung, Steuerung und Kollisionen.
 */
class BallBehavior : Component() {
    private val log = logger<BallBehavior>()
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
            log.debug("Ball Position: x=${transform.x}, y=${transform.y}")
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
        log.debug("Starting Ball...")
        if (dx == 0f && dy == 0f) { // Nur starten, wenn der Ball ruht
            dx = if ((0..1).random() == 0) 1f else -1f // Zufällige Richtung
            dy = if ((0..1).random() == 0) 1f else -1f
            log.debug("Ball gestartet: dx=$dx, dy=$dy")
        } else {
            log.debug("Ball nicht gestartet: dx=$dx, dy=$dy")
        }
    }

    fun onSpacePressed() {
        log.debug("Leertaste wurde gedrückt. Ball wird gestartet.")
        startBall()
    }
}
