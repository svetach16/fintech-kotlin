package org.tinkoff.fintech.lesson2

import java.time.LocalDate
import java.time.temporal.ChronoUnit

interface Transport {
    val manufacturer: String
    val creationDate: LocalDate

    fun go()
}

fun Transport.isNew() = ChronoUnit.YEARS.between(creationDate, LocalDate.now()) < 1