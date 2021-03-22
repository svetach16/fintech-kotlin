import java.sql.ResultSet
import java.sql.SQLException

data class Student(
    val name: String,
    val age: Int,
    val groupId: Int
)

data class StudentWithGroup(
    val name: String,
    val age: Int,
    val groupId: Int,
    val courseNumber: Int,
    val department: String
)

data class Teacher(
    val name: String,
    val subject: String,
    val yearsOfExperience: Int
)

class StudentService(private val client: DatabaseClient) {
    fun getStudent(id: Int): Student = getSingle(
        GET_STUDENT_BY_ID,
        id,
        mapper = STUDENT_MAPPER
    )

    fun getStudentsOfGroup(groupId: Int): List<Student> = getList(
        GET_STUDENT_BY_GROUP_ID,
        groupId,
        mapper = STUDENT_MAPPER
    )

    fun getStudentsWithGroup(): List<StudentWithGroup> = getList(
        JOIN_STUDENTS_WITH_GROUP,
        mapper = STUDENT_WITH_GROUP_MAPPER
    )

    fun getGroupSize(): Map<Int, Int> = getList(GET_GROUP_SIZE) {
        it.getInt("group_id") to it.getInt("size")
    }.toMap()

    fun getTeachersOrderedByExperience(): List<Teacher> = getList(
        ORDER_TEACHER_BY_EXPERIENCE,
        mapper = TEACHER_MAPPER
    )

    private fun <T> getSingle(
        sql: String,
        vararg params: Any,
        mapper: (ResultSet) -> T
    ): T = getList(sql, *params, mapper = mapper).single()

    private fun <T> getList(
        sql: String,
        vararg params: Any,
        mapper: (ResultSet) -> T
    ): List<T> {
        val resultSet = client.executeQuery(sql, *params)
        val result = ArrayList<T>()

        while (resultSet.next()) {
            result += mapper(resultSet)
        }

        return result
    }

    companion object {
        private const val GET_ALL_STUDENTS = "SELECT * FROM STUDENT"
        private const val GET_STUDENT_BY_ID = "$GET_ALL_STUDENTS WHERE id = ?"
        private const val GET_STUDENT_BY_GROUP_ID = "$GET_ALL_STUDENTS WHERE group_id > ?"
        private const val JOIN_STUDENTS_WITH_GROUP =
            """
            SELECT student.id, name, age, group_id, StudentGroup.course_number, department
                FROM student
                       JOIN StudentGroup
                            ON student.group_id = StudentGroup.id    
            """

        private const val GET_GROUP_SIZE =
            """
            SELECT group_id, count(*) as size
            FROM student
            GROUP BY group_id
            """

        private const val ORDER_TEACHER_BY_EXPERIENCE =
            """
            SELECT * FROM teacher
            ORDER BY years_of_experience DESC
            """

        private val STUDENT_WITH_GROUP_MAPPER = { resultSet: ResultSet ->
            StudentWithGroup(
                resultSet.getString("name"),
                resultSet.getInt("age"),
                resultSet.getInt("group_id"),
                resultSet.getInt("course_number"),
                resultSet.getString("department")
            )
        }

        private val TEACHER_MAPPER = { resultSet: ResultSet ->
            Teacher(
                resultSet.getString("name"),
                resultSet.getString("subject"),
                resultSet.getInt("years_of_experience")
            )
        }

        private val STUDENT_MAPPER = { resultSet: ResultSet ->
            Student(
                resultSet.getString("name"),
                resultSet.getInt("age"),
                resultSet.getInt("group_id")
            )
        }
    }
}


fun main() {
    try {
        DatabaseClient.h2InMemory().use { client ->
            val databaseInitializer = DatabaseInitializer(client).apply { init() }
            val service = StudentService(client)

            println("Get student by id:")
            println(service.getStudent(id = 1))
            println("\nGet students filtered by group:")
            service.getStudentsOfGroup(groupId = 511).forEach(System.out::println)
            println("\nGet students with group info:")
            service.getStudentsWithGroup().forEach(System.out::println)
            println("\nGet group size:")
            service.getGroupSize().forEach(System.out::println)
            println("\nGet teachers ordered by experience:")
            service.getTeachersOrderedByExperience().forEach(System.out::println)

            databaseInitializer.cleanup()
        }
    } catch (e: SQLException) {
        println("Got exception: $e")
    }
}