package org.fintech.tinkoff

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.sql.DataSource

@RestController
@RequestMapping("api")
class StudentMetaController(
    private val dataSource: DataSource
) {
    private var logger = LoggerFactory.getLogger(StudentMetaController::class.java)

    @GetMapping(value = ["/students/meta/{id}"])
    fun getMetaInfoById(@PathVariable id: Int): StudentInfo? {
        logger.info("Get student meta info with id: $id")

        return dataSource.connection.use { connection ->
            val statement = connection.prepareStatement("SELECT * FROM StudentInfo WHERE student_id = ?").apply {
                setInt(1, id)
            }

            statement.executeQuery().use {
                    if (it.next()) {
                        StudentInfo(
                            it.getString("faculty"),
                            it.getInt("height"),
                            it.getInt("weight")
                        )
                    } else {
                        null
                    }
            }
        }
    }
}
