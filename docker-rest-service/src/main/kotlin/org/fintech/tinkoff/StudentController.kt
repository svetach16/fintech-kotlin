package org.fintech.tinkoff

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import javax.sql.DataSource

@RestController
@RequestMapping("api")
class StudentController(
    private val dataSource: DataSource
) {
    private var logger = LoggerFactory.getLogger(StudentController::class.java)
    private val client = RestTemplate()

    @GetMapping(value = ["/students/{id}"])
    fun getById(@PathVariable id: Int): Student {
        logger.info("Get student with id: $id")

        return dataSource.connection.use { connection ->
            val statement = connection.prepareStatement("SELECT * FROM STUDENT WHERE id = ?").apply {
                setInt(1, id)
            }
            val info = client.getForObject<StudentInfo?>("http://docker-rest-service-meta:8080/api/students/meta/$id")

            statement.executeQuery().use {
                generateSequence {
                    if (it.next()) {
                        Student(
                            it.getString("name"),
                            it.getString("surname"),
                            info
                        )
                    } else {
                        null
                    }
                }.single()
            }
        }
    }
}