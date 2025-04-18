package game

import engine.GameObject
import engine.Scene
import engine.Transform
import utils.Key

fun setupGame(
    gameWitdth: Float,
    gameHeight: Float,
) {
    val left =
        GameObject("Left Paddle").apply {
            addComponent(Transform(50f, gameHeight / 2 - 50, 20f, 100f))
            addComponent(Paddle(Key.W, Key.S))
        }

    val right =
        GameObject("Right Paddle").apply {
            addComponent(Transform(gameWitdth - 70f, gameHeight / 2 - 50, 20f, 100f))
            addComponent(Paddle(Key.DirectionUp, Key.DirectionDown))
        }

    val ball =
        GameObject("Ball").apply {
            addComponent(Transform(gameWitdth / 2, gameHeight / 2, 20f, 20f))
            addComponent(Ball(gameWitdth, gameHeight))
        }

    Scene.gameObjects.addAll(listOf(left, right, ball))
    println("Game objects initialized: ${Scene.gameObjects.size}")
}
