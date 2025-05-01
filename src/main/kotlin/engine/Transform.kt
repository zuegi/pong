package engine

// engine/Transform.kt
data class Transform(
    var x: Float,
    var y: Float,
    val width: Float,
    val height: Float,

    // Bewegungsgeschwindigkeit
    var velocityX: Float = 0f,
    var velocityY: Float = 0f
) : Component()
