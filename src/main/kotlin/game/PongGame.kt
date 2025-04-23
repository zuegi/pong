package game

import engine.GameObject
import engine.Scene
import engine.Transform
import org.slf4j.LoggerFactory
import ui.BallBehavior
import ui.PaddleInput
import utils.Key

private val log = LoggerFactory.getLogger("PongGame")

fun setupGame(
    gameWitdth: Float,
    gameHeight: Float,
) {
    val left =
        GameObject("Left Paddle").apply {
            addComponent(Transform(50f, gameHeight / 2 - 50, 20f, 100f))
            addComponent(Paddle(Key.W, Key.X))
            addComponent(PaddleInput(Key.W, Key.X))
        }

    val right =
        GameObject("Right Paddle").apply {
            addComponent(Transform(gameWitdth - 70f, gameHeight / 2 - 50, 20f, 100f))
            addComponent(Paddle(Key.DirectionUp, Key.DirectionDown))
            addComponent(PaddleInput(Key.DirectionUp, Key.DirectionDown))
        }

    val ball =
        GameObject("Ball").apply {
            addComponent(Transform(gameWitdth / 2, gameHeight / 2, 20f, 20f))
            addComponent(BallBehavior()) // BallBehavior hinzufügen
            addComponent(Ball())
        }

    Scene.gameObjects.addAll(listOf(left, right, ball))
    log.debug("Game objects initialized: ${Scene.gameObjects.size}")
}
