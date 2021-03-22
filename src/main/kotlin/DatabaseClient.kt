import java.sql.DriverManager
import java.sql.ResultSet

class DatabaseClient private constructor(
    url: String,
    user: String,
    password: String
) : AutoCloseable {
    private val connection = DriverManager.getConnection(url, user, password)


    fun execute(sql: String) {
        connection.createStatement().execute(sql)
    }

    fun executeQuery(sql: String, vararg params: Any): ResultSet {
        val statement = connection.prepareStatement(sql)

        for (i in params.indices) {
            statement.setObject(i + 1, params[i])
        }

        return statement.executeQuery()
    }

    override fun close() {
        connection.close()
    }


    companion object {
        fun h2InMemory() = DatabaseClient("jdbc:h2:mem:", "", "")
    }
}