import java.util.*

interface Queue<T> {
    fun enqueue(x: T)
    fun dequeue(): T?
}

class LinkedListQueue<T> : Queue<T> {
    private val list = LinkedList<T>()

    override fun enqueue(x: T) {
        list.addLast(x)
    }

    override fun dequeue(): T = list.pollFirst()

    override fun toString() = "$list"
}

fun <T> queueOf(vararg elements: T): Queue<T> = LinkedListQueue<T>().apply { elements.forEach { enqueue(it) } }