package org.tinkoff.fintech.lesson2

import java.time.LocalDate

class Motorcycle(
    override val manufacturer: String,
    override val creationDate: LocalDate,
    override val horsePower: Int
) : TransportWithEngine {
    private var remainingGasoline = 100

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