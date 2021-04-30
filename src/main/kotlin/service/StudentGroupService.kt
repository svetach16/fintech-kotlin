package service

import model.StudentGroup
import model.StudentGroupTable
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

interface StudentGroupService {
    fun create(name: String): Int
    fun get(id: Int): StudentGroup
    fun delete(id: Int)
}

object DatabaseStudentGroupService : StudentGroupService {
    override fun create(name: String): Int {
        return transaction {
            StudentGroupTable.insert {
                it[StudentGroupTable.name] = name
            }
        } get StudentGroupTable.id
    }

    override fun get(id: Int): StudentGroup {
        return transaction {
            val row = StudentGroupTable.select { StudentGroupTable.id eq id }.single()

            StudentGroup(row[StudentGroupTable.name])
        }
    }

    override fun delete(id: Int) {
        transaction {
            StudentGroupTable.deleteWhere { StudentGroupTable.id eq id }
        }
    }
}