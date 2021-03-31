import kotlin.concurrent.thread

class SharedMutableState {
    var i = 0

    fun read(): Int = synchronized(this) {
        Thread.sleep(50)
        i
    }


    fun write(): Int = synchronized(this) {
        Thread.sleep(50)
        ++i
    }
}

fun main() {
    val state = SharedMutableState()

    // writer
    thread {
        repeat(30) {
            println("Thread ${Thread.currentThread().id} write ${state.write()}")
        }
    }

    // readers
    (1..5).map {
        thread {
            repeat(30) {
                println("Thread ${Thread.currentThread().id} read ${state.read()}")
            }
        }
    }
}