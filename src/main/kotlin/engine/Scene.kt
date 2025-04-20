package engine

import androidx.compose.runtime.mutableStateListOf

// engine/GameLoop.kt
object Scene {
    val gameObjects = mutableStateListOf<GameObject>()

    fun update(deltaTime: Float) {
        gameObjects.forEach { gameObject ->
            gameObject.components().forEach { component ->
                component.update(deltaTime)
            }
        }
    }

    fun render(drawScope: androidx.compose.ui.graphics.drawscope.DrawScope) {
        gameObjects.forEach { gameObject ->
            gameObject.components().forEach { component ->
                println("Rendering ${component::class.simpleName}")
                component.render(drawScope)
            }
        }
    }
}
