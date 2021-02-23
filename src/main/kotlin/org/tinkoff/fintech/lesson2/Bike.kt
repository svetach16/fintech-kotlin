package org.tinkoff.fintech.lesson2

import java.time.LocalDate

class Bike(
    override val manufacturer: String,
    override val creationDate: LocalDate
) : Transport {
    override fun go()  {
        println("Go ahead")
    }

    override fun toString(): String = "Bike(manufacturer='$manufacturer', creationDate=$creationDate)"
}