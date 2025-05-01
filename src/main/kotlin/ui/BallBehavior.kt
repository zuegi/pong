package ui

import engine.Component
import engine.Transform
import engine.Scene
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
        if (deltaTime <= 0) return

        val transform = gameObject?.getComponent<Transform>() ?: return

        // Bewegung des Balls
        if (dx != 0f || dy != 0f) {
            transform.x += dx * speed * deltaTime
            transform.y += dy * speed * deltaTime
            log.debug("Ball Position: x=${transform.x}, y=${transform.y}")
        }

        // Kollision mit oberem/unterem Bildschirmrand
        if (transform.y < 0 || transform.y > GameConfig.gameHeight - transform.height) {
            dy *= -1
            log.debug("⬆️⬇️ Ball hat obere/untere Wand getroffen – dy umgekehrt: dy=$dy")
        }

        // Kollision mit beiden Paddles prüfen
        listOf("Left Paddle", "Right Paddle").forEach { paddleName ->
            val paddle = Scene.findGameObjectByName(paddleName)
            val paddleTransform = paddle?.getComponent<Transform>()
            if (paddleTransform != null) {
                checkCollision(paddleTransform)
            }
        }

        // Zurücksetzen, wenn der Ball das Spielfeld verlässt
        if (transform.x < 0 || transform.x > GameConfig.gameWidth) {
            resetBall(transform)
        }
    }

    private fun resetBall(transform: Transform) {
        transform.x = GameConfig.gameWidth / 2 - transform.width / 2
        transform.y = GameConfig.gameHeight / 2 - transform.height / 2
        dx = if (dx > 0) -1f else 1f
        dy = 1f
        log.debug("🔁 Ball zurückgesetzt")
    }

    fun startBall() {
        log.debug("Starting Ball...")
        if (dx == 0f && dy == 0f) {
            dx = if ((0..1).random() == 0) 1f else -1f
            dy = if ((0..1).random() == 0) 1f else -1f
            log.debug("Ball gestartet: dx=$dx, dy=$dy")
        } else {
            log.debug("Ball bereits unterwegs: dx=$dx, dy=$dy")
        }
    }

    fun onSpacePressed() {
        log.debug("Leertaste wurde gedrückt. Ball wird gestartet.")
        startBall()
    }

    private fun checkCollision(paddleTransform: Transform) {
        val transform = gameObject?.getComponent<Transform>() ?: return

        val ballLeft = transform.x
        val ballRight = transform.x + transform.width
        val ballTop = transform.y
        val ballBottom = transform.y + transform.height

        val paddleLeft = paddleTransform.x
        val paddleRight = paddleTransform.x + paddleTransform.width
        val paddleTop = paddleTransform.y
        val paddleBottom = paddleTransform.y + paddleTransform.height

        val isColliding =
            ballRight >= paddleLeft &&
                    ballLeft <= paddleRight &&
                    ballBottom >= paddleTop &&
                    ballTop <= paddleBottom

        println("Ball:   L=$ballLeft R=$ballRight T=$ballTop B=$ballBottom")
        println("Paddle: L=$paddleLeft R=$paddleRight T=$paddleTop B=$paddleBottom")

        if (isColliding) {
            dx *= -1
            println("🎯 Ball hat Paddle getroffen! Neue Richtung: dx=$dx, dy=$dy")
        }
    }
}
