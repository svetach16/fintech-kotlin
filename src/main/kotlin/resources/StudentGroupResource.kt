package resources

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import model.CreateStudentGroupDto
import service.DatabaseStudentGroupService
import service.DatabaseStudentService

fun Route.studentGroup() {
    route("/api/groups") {
        get("/{id}") {
            val id = requireNotNull(call.parameters["id"]?.toInt())

            call.respond(HttpStatusCode.OK, DatabaseStudentGroupService.get(id))
        }
        get("/{id}/students") {
            val id = requireNotNull(call.parameters["id"]?.toInt())

            call.respond(HttpStatusCode.OK, DatabaseStudentService.getByGroupId(id))
        }
        post("/") {
            val dto = call.receive<CreateStudentGroupDto>()

            call.respond(HttpStatusCode.OK, DatabaseStudentGroupService.create(dto.name))
        }
        delete("/{id}") {
            val id = requireNotNull(call.parameters["id"]?.toInt())

            DatabaseStudentGroupService.delete(id)

            call.respond(HttpStatusCode.NoContent)
        }
    }
}