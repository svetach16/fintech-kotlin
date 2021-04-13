package org.tinkoff.fintech.model

data class Student(
    val id: Int,
    val name: String,
    val surname: String,
    val faculty: Faculty
)

data class Faculty(
    val id: Int,
    val name: String
)