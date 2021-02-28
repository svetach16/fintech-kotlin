package org.tinkoff.fintech.lesson2

import java.time.LocalDate

class Bike(
    override val manufacturer: String,
    override val creationDate: LocalDate
) : Transport {
    fun repairChain() {
        println("Chain has been repaired!")
    }

    override fun go()  {
        println("Go ahead")
    }

    override fun toString(): String = "Bike(manufacturer='$manufacturer', creationDate=$creationDate)"
}