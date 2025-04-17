package engine

import androidx.compose.ui.graphics.drawscope.DrawScope
import kotlin.reflect.KClass

class GameObject {
    private val components = mutableListOf<Component>()

    fun update(deltaTime: Float) = components.forEach { it.update(deltaTime) }

    fun render(drawScope: DrawScope) = components.forEach { it.render(drawScope) }

    fun <T : Component> getComponent(type: KClass<T>) = components.firstOrNull { type.isInstance(it) } as? T

    inline fun <reified T : Component> getComponent() = getComponent(T::class)

    fun addComponent(component: Component) {
        component.gameObject = this
        components.add(component)
    }
}
