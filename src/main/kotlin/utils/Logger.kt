package utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Für Klassen oder Objekte – generisch & lazy geeignet.
 */
inline fun <reified T : Any> loggerFor(): Logger = LoggerFactory.getLogger(T::class.java)

/**
 * Extension für `this.logger()` in jeder beliebigen Klasse oder jedem Objekt.
 */
fun <R : Any> R.logger(): Logger = LoggerFactory.getLogger(this::class.java)

/**
 * Für Funktionen oder Composables, wo kein `this` verfügbar ist.
 * z. B.: val log = logger("GameCanvas")
 */
fun logger(name: String): Logger = LoggerFactory.getLogger(name)
