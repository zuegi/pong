

class GameObject {
    val components = mutableListOf<Component>()
    val transform = Transform()

    init {
        addComponent(transform)
    }

    fun addComponent(component: Component) {
        components.add(component)
    }

    inline fun <reified T : Component> getComponent(): T? = components.filterIsInstance<T>().firstOrNull()

    fun start() {
        components.forEach { it.start() }
    }

    fun update(deltaTime: Float) {
        components.forEach { it.update(deltaTime) }
    }
}
