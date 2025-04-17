package utils

import java.awt.KeyboardFocusManager
import java.awt.event.KeyEvent

// utils/Keyboard.kt
object Keyboard {
    private val pressed = mutableSetOf<Key>()

    init {
        KeyboardFocusManager
            .getCurrentKeyboardFocusManager()
            .addKeyEventDispatcher {
                if (it.id == KeyEvent.KEY_PRESSED) {
                    Key.entries.find { key -> key.keyCode == it.keyCode }?.let { pressed.add(it) }
                }
                if (it.id == KeyEvent.KEY_RELEASED) {
                    Key.entries.find { key -> key.keyCode == it.keyCode }?.let { pressed.remove(it) }
                }
                false
            }
    }

    fun isKeyPressed(key: Key) = key in pressed
}
