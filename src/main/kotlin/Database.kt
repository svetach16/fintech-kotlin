import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import model.StudentGroupTable
import model.StudentTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseUtil {
    fun init() {
        Database.connect(hikari())
        transaction {
            create(StudentTable)
            create(StudentGroupTable)
            migrate()
        }
    }

    fun migrate() {
        StudentGroupTable.insert { it[id] = 1; it[name] = "Biophysics" }
        StudentGroupTable.insert { it[id] = 2; it[name] = "Mathematics" }
        StudentTable.insert { it[id] = 1; it[name] = "Vasya"; it[surname] = "Pupkin"; it[groupId] = 1; }
    }

    fun clear() {
        transaction {
            SchemaUtils.drop(StudentTable)
            SchemaUtils.drop(StudentGroupTable)
        }
    }

    private fun hikari(): HikariDataSource {
        val config = HikariConfig().apply {
            driverClassName = "org.h2.Driver"
            jdbcUrl = "jdbc:h2:mem:db"
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        }

        return HikariDataSource(config)
    }
}