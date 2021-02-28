package org.tinkoff.fintech.lesson2

import java.time.LocalDate

class Airplane(
    override val manufacturer: String,
    override val creationDate: LocalDate,
    override val horsePower: Int
) : TransportWithEngine {
    private var remainingKerosene = 100

    fun removeChassis() {
        println("Chassis has been removed!")
    }

    override fun refuel() {
        remainingKerosene = 100
    }

    override fun go() {
        if (remainingKerosene > 0) {
            println("Go ahead")
            remainingKerosene -= 50
        } else {
            println("No gasoline")
        }
    }

    override fun toString(): String =
        "Airplane(manufacturer='$manufacturer', creationDate=$creationDate, horsePower=$horsePower)"
}