package org.tinkoff.fintech.lesson2

import java.lang.Integer.max
import java.time.LocalDate
import kotlin.math.min

class Motorcycle(
    override val manufacturer: String,
    override val creationDate: LocalDate,
    override val horsePower: Int
) : TransportWithEngine {
    private var remainingGasoline = 100
    private var transmission = 0

    fun decreaseTransmission() {
        transmission = max(transmission - 1, 0)
        println("Transmission changed to $transmission")
    }

    fun increaseTransmission() = increaseTransmission(1)

    fun increaseTransmission(toAdd: Int) {
        transmission = min(transmission + toAdd, 5)
        println("Transmission changed to $transmission")
    }

    override fun refuel() {
        remainingGasoline = 100
    }

    override fun go() {
        if (remainingGasoline > 0) {
            println("Go ahead")
            remainingGasoline -= 25
        } else {
            println("No gasoline")
        }
    }

    override fun toString(): String =
        "Motorcycle(manufacturer='$manufacturer', creationDate=$creationDate, horsePower=$horsePower)"
}