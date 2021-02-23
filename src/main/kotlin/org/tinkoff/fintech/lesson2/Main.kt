package org.tinkoff.fintech.lesson2

import java.time.LocalDate

fun main() {
    val stork = Bike("Аист", LocalDate.of(2000, 1, 1))
    val superSport950 = Motorcycle("Ducati", LocalDate.now(), 116)
    val boeing737 = Airplane("Boeing", LocalDate.of(1975, 1, 1), 100_000)
    val transports = listOf(stork, superSport950, boeing737)

    println("Модели транспорта:")
    transports.forEach { println(it) }

    println()

    println("Устаревшие модели:")
    transports.filterNot { it.isNew() }.forEach { println(it) }

    println()

    println("Тест:")
    repeat(5) {
        transports.forEach { print("${it.manufacturer}: "); it.go() }
        println()
    }
}