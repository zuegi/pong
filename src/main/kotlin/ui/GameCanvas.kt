package ui

import DebugPanel
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import engine.Scene
import game.GameConfig.debugPanelWidth
import game.GameConfig.gameHeight
import game.GameConfig.gameWidth

/**
 Die GameCanvas-Klasse ist eine zentrale Komponente für die Darstellung und Interaktion des Spiels.
 Sie hat folgende Einsatzzwecke:

 1. Zeichenfläche für das Spiel:
 Die Canvas-Komponente wird verwendet, um alle Spielobjekte (Scene.render) zu zeichnen,
 einschließlich des Balls, der Spielfeldgrenzen und anderer visueller Elemente.
 2. Eingabeverarbeitung:
 Die Klasse verarbeitet Benutzereingaben, wie z. B. das Drücken der Leertaste (Key.Spacebar),
 und leitet diese an die entsprechenden Spielobjekte weiter (z. B. BallBehavior.onSpacePressed).
 3. Fokussteuerung:
 Sie stellt sicher, dass die Canvas-Komponente den Fokus erhält, damit Tasteneingaben korrekt erfasst werden können.
 4. Integration mit der Spielszene:
 Die GameCanvas-Klasse ist eng mit der Scene-Klasse verbunden, da sie deren render-Methode aufruft,
 um den aktuellen Zustand des Spiels darzustellen.
 5. Debugging-Unterstützung:
 Sie integriert ein DebugPanel, das zusätzliche Informationen oder Debugging-Daten anzeigen kann.

 Zusammengefasst dient die GameCanvas-Klasse als Schnittstelle zwischen der Spielszene, der Benutzerinteraktion
 und der grafischen Darstellung.
 */

@Suppress("FunctionName")
@Composable
fun GameCanvas(frameTrigger: Long) {
    println("GameCanvas recomposed")
    val focusRequester = FocusRequester()

    Canvas(
        modifier =
            Modifier
                .fillMaxSize()
                .background(Color.Black)
                .focusRequester(focusRequester)
                .focusable()
                .onKeyEvent { event ->
                    if (event.type == KeyEventType.KeyDown && event.key == Key.Spacebar) {
                        Scene.gameObjects.forEach { gameObject ->
                            gameObject.getComponent<BallBehavior>()?.onSpacePressed()
                        }
                        true
                    } else {
                        false
                    }
                },
    ) {
        println("🔄 Canvas drawing frame $frameTrigger") // zur Kontrolle
        Scene.render(this)
    }

    // Fokus anfordern, damit die Eingabe funktioniert
    androidx.compose.runtime.LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    DebugPanel(
        x = gameWidth,
        y = 10f,
        width = debugPanelWidth,
        height = gameHeight,
        frameTrigger = frameTrigger,
    )
}
