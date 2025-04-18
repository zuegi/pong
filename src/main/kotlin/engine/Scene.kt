package engine

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.drawscope.DrawScope

// engine/GameLoop.kt
object Scene {
    val gameObjects = mutableStateListOf<GameObject>()

    fun update(deltaTime: Float) = gameObjects.forEach { it.update(deltaTime) }

    fun render(drawScope: DrawScope) = gameObjects.forEach { it.render(drawScope) }
}
