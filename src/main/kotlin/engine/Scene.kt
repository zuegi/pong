package engine

import androidx.compose.runtime.mutableStateListOf
import utils.logger

// engine/GameLoop.kt
object Scene {
    private val log = logger()
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
                log.debug("Rendering ${component::class.simpleName}")
                component.render(drawScope)
            }
        }
    }
}
