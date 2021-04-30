package resources

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import model.CreateStudentDto
import model.StudentTransferDto
import service.DatabaseStudentService

fun Route.student() {
    route("/api/students") {
        get("/{id}") {
            val id = requireNotNull(call.parameters["id"]?.toInt())

            call.respond(HttpStatusCode.OK, DatabaseStudentService.get(id))
        }
        post("/") {
            val dto = call.receive<CreateStudentDto>()
            val id = DatabaseStudentService.create(
                dto.name,
                dto.surname,
                dto.groupId
            )
            call.respondText("$id")
        }
        post("/transfer") {
            val dto = call.receive<StudentTransferDto>()

            DatabaseStudentService.transfer(
                dto.studentId,
                dto.toGroupId
            )

            call.respond(HttpStatusCode.NoContent)
        }
        delete("/{id}") {
            val id = requireNotNull(call.parameters["id"]?.toInt())

            DatabaseStudentService.delete(id)

            call.respond(HttpStatusCode.NoContent)
        }
    }
}