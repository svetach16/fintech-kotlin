package org.tinkoff.fintech.controller

import org.springframework.stereotype.Component
import org.tinkoff.fintech.model.Faculty

interface FacultyService {
    fun get(studentId: Int) : Faculty?
}

@Component
class InMemoryFacultyService : FacultyService {
    override fun get(studentId: Int): Faculty? {
        return when (studentId) {
            1 -> Faculty(1, "Биофак")
            2 -> Faculty(1, "Физфак")
            else -> return null
        }
    }
}