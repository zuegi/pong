package utils

import java.awt.event.KeyEvent

// utils/Key.kt
enum class Key(
    val keyCode: Int,
) {
    W(KeyEvent.VK_W),
    S(KeyEvent.VK_S),
    A(KeyEvent.VK_A),
    D(KeyEvent.VK_D),
    DirectionUp(KeyEvent.VK_UP),
    DirectionDown(KeyEvent.VK_DOWN),
    DirectionLeft(KeyEvent.VK_LEFT),
    DirectionRight(KeyEvent.VK_RIGHT),
    Space(KeyEvent.VK_SPACE),
    Escape(KeyEvent.VK_ESCAPE),
    // ggf. mehr hinzufügen
}
