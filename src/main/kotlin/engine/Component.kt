package engine

import androidx.compose.ui.graphics.drawscope.DrawScope

// engine/Component.kt
abstract class Component {
    lateinit var gameObject: GameObject

    open fun update(deltaTime: Float) {}

    open fun render(drawScope: DrawScope) {}
}
