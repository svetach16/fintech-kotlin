import kotlinx.coroutines.*

fun sendEmail(address: String, message: String) {
    println("Sent '$$message' to $address")
}

suspend fun getAddress(): String {
    delay(3000)

    println("Got email address")
    return "coroutine@kotlin.org"
}

suspend fun getMessage(): String {
    delay(5000)

    println("Email body has been created")
    return "Hey there!"
}

fun CoroutineScope.sendEmailSuspending() = launch {
    val message = async { getMessage() }
    val address = async { getAddress() }

    println("Waiting for email data")

    sendEmail(address.await(), message.await())
}

fun main() {
    runBlocking { sendEmailSuspending() }
}