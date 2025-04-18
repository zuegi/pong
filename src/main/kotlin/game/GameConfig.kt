package game

object GameConfig {
    val gameWidth = 1000f
    val gameHeight = 800f
    val debugPanelWidth = 400f
    val windowWidth = gameWidth + debugPanelWidth

    val frameTime = 16L // ~60 FPS

    val ballSpeed = 200f // Pixel pro Sekunde
    val paddleSpeed = 300f // Pixel pro Sekunde
}
