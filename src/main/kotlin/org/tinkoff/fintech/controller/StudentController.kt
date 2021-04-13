package org.tinkoff.fintech.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import org.tinkoff.fintech.model.Student
import org.tinkoff.fintech.service.StudentService

@RestController
@RequestMapping("/api/student")
class StudentController(private val service: StudentService) {
    @GetMapping("/{id}")
    @Operation(summary = "Get student by id")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Student found"),
            ApiResponse(responseCode = "404", description = "Student not found")
        ]
    )
    fun get(@PathVariable id: Int): Student {
        return service.get(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Student with id: $id not found")
    }

    @GetMapping
    @Operation(summary = "Get all students")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Student found"),
            ApiResponse(responseCode = "404", description = "Student not found")
        ]
    )
    fun getAll(): List<Student> {
        return service.getAll()
    }

    @PostMapping
    @Operation(summary = "Update student")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Student updated"),
        ]
    )
    fun update(@RequestBody student: Student) {
        log.info("Updating student: $student")
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove student by id")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Student deleted"),
        ]
    )
    fun delete(@PathVariable id: Int) {
        log.info("Deleting student with id: $id")
    }

    companion object {
        private val log = LoggerFactory.getLogger(StudentController::class.java);
    }
}