import kotlinx.coroutines.*
import kotlinx.coroutines.channels.produce

class EventService {
    @ExperimentalCoroutinesApi
    fun subscribe(scope: CoroutineScope) = scope.produce {
        var x = 1

        while (true) {
            delay(25)
            send(x++)
        }
    }
}

@ExperimentalCoroutinesApi
fun main() {
    runBlocking {
        val eventService = EventService()
        val channel = eventService.subscribe(this)

        repeat(30) {
            println(channel.receive())
        }

        coroutineContext.cancelChildren()
    }
}