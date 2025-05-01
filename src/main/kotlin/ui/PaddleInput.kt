package ui

import engine.Component
import engine.GameObject
import engine.Transform
import game.GameConfig
import utils.Key
import utils.Keyboard


class PaddleInput(
    private val upKey: Key,
    private val downKey: Key,
) : Component() {
    override fun update(deltaTime: Float) {
        val transform = gameObject.getComponent<Transform>() ?: return

        if (Keyboard.isKeyPressed(upKey)) {
            transform.y -= GameConfig.ballSpeed * deltaTime
        }
        if (Keyboard.isKeyPressed(downKey)) {
            transform.y += GameConfig.ballSpeed * deltaTime
        }
    }
}
