import kotlin.concurrent.thread

fun printName() = println(Thread.currentThread().name)

class RunThread : Runnable {
    override fun run() {
        printName()
    }
}

class MyThread : Thread() {
    override fun run() {
        printName()
    }
}

fun main() {
    MyThread().apply { start() }
    Thread(RunThread()).apply { start() }
    thread { printName() }
    Thread { printName() }.start()
    thread(isDaemon = true) { printName() }
    thread(priority = 1) { printName() }
    thread(priority = 9) { printName() }
}