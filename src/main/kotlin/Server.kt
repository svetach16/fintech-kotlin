import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.exc.MismatchedInputException
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.jackson.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import resources.student
import resources.studentGroup

fun Application.module() {
    install(CallLogging)
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }
    install(StatusPages) {
        exception<IllegalArgumentException> { call.respond(HttpStatusCode.BadRequest, it.message ?: "") }
        exception<MismatchedInputException> { call.respond(HttpStatusCode.BadRequest, it.message ?: "") }
        exception<NoSuchElementException> { call.respond(HttpStatusCode.NotFound, it.message ?: "") }
    }
    install(Routing) {
        student()
        studentGroup()
    }

    DatabaseUtil.init()

    environment.monitor.subscribe(ApplicationStopped) { DatabaseUtil.clear() }
}

fun main(args: Array<String>) {
    embeddedServer(CIO, commandLineEnvironment(args)).start(wait = true)
}
