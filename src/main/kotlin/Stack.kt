import kotlin.collections.ArrayList

interface Stack<T> {
    fun push(x: T)
    fun pop(): T?
}

class ArrayStack<T> : Stack<T> {
    private val list = ArrayList<T>()

    override fun push(x: T) {
        list.add(x)
    }

    override fun pop(): T? = if (list.isEmpty()) null else list.removeAt(list.lastIndex)

    override fun toString() = "$list"
}

fun <T> stackOf(vararg elements: T): Stack<T> = ArrayStack<T>().apply { elements.forEach { push(it) } }