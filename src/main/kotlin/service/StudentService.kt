package service

import model.Student
import model.StudentGroupTable
import model.StudentTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

interface StudentService {
    fun create(name: String, surname: String, groupId: Int): Int
    fun delete(id: Int)
    fun get(id: Int): Student
    fun getByGroupId(groupId: Int): List<Student>
    fun transfer(studentId: Int, toGroupId: Int)
}

object DatabaseStudentService : StudentService {
    override fun delete(id: Int) {
        transaction {
            StudentTable.deleteWhere { StudentTable.id eq id }
        }
    }

    override fun create(name: String, surname: String, groupId: Int): Int {
        return transaction {
            StudentTable.insert {
                it[StudentTable.name] = name
                it[StudentTable.surname] = surname
                it[StudentTable.groupId] = groupId
            }
        } get StudentTable.id
    }

    override fun get(id: Int): Student {
        return transaction {
            val row = (StudentTable innerJoin StudentGroupTable)
                .select { StudentTable.id eq id }.single()

            Student(row[StudentTable.name], row[StudentTable.surname], row[StudentGroupTable.name])
        }
    }

    override fun getByGroupId(groupId: Int): List<Student> {
        return transaction {
            val rows = (StudentTable innerJoin StudentGroupTable)
                .select { StudentTable.groupId eq groupId }

            rows.map { row -> Student(row[StudentTable.name], row[StudentTable.surname], row[StudentGroupTable.name]) }
        }
    }

    override fun transfer(studentId: Int, toGroupId: Int) {
        transaction {
            StudentTable.update({ StudentTable.id eq studentId }) {
                it[groupId] = toGroupId
            }
        }
    }
}