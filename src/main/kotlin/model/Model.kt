package model

import org.jetbrains.exposed.sql.Table

object StudentTable : Table() {
    val id = integer("id").autoIncrement()
    val groupId = reference("group_id", StudentGroupTable.id)
    val name = text("name")
    val surname = text("surname")

    override val primaryKey = PrimaryKey(id)
}

object StudentGroupTable : Table() {
    val id = integer("id").autoIncrement()
    val name = text("name")

    override val primaryKey = PrimaryKey(id)
}

data class Student(
    val name: String,
    val surname: String,
    val groupName: String
)

data class StudentGroup(
    val name: String
)

data class StudentTransferDto(
    val studentId: Int,
    val toGroupId: Int,
)

data class CreateStudentDto(
    val name: String,
    val surname: String,
    val groupId: Int
)

data class CreateStudentGroupDto(
    val name: String
)