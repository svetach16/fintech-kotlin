package org.tinkoff.fintech.lesson2

interface TransportWithEngine : Transport {
    val horsePower: Int

    fun refuel()
}

