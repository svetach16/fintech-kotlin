package org.tinkoff.fintech.service

import org.springframework.stereotype.Component
import org.tinkoff.fintech.controller.FacultyService
import org.tinkoff.fintech.model.Student

interface StudentService {
    fun get(id: Int): Student?
    fun getAll(): List<Student>
    fun update(student: Student)
    fun delete(id: String)
}

@Component
class InMemoryStudentService(private val facultyService: FacultyService) : StudentService {
    override fun get(id: Int): Student? {
        return when (id) {
            1 -> facultyService.get(id)?.let { Student(id, "Петр", "Иванов", it) }
            2 -> facultyService.get(id)?.let { Student(id, "Василий", "Пупкин", it) }
            else -> return null
        }
    }

    override fun getAll(): List<Student> = (1..2).mapNotNull { get(it) }

    override fun update(student: Student) = Unit

    override fun delete(id: String) = Unit
}