package engine

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.drawscope.DrawScope
import utils.logger

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

    fun render(drawScope: DrawScope) {
        gameObjects.forEach { gameObject ->
            gameObject.components().forEach { component ->
                log.debug("Rendering ${component::class.simpleName}")
                component.render(drawScope)
            }
        }
    }

    // 🔍 HINZUGEFÜGT: GameObject per Name finden
    fun findGameObjectByName(name: String): GameObject? {
        return gameObjects.find { it.name == name }
    }

    // 🔧 Optional: GameObject hinzufügen
    fun addGameObject(gameObject: GameObject) {
        gameObjects.add(gameObject)
    }
}
