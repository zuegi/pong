package engine

// engine/Transform.kt
data class Transform(
    var x: Float,
    var y: Float,
    val width: Float,
    val height: Float,
) : Component()
