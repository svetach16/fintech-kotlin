fun main() {
    val queue = queueOf(1, 2, 3)
    println("Queue: $queue")

    println("Dequeue: ${queue.dequeue()}")
    println("Dequeue: ${queue.dequeue()}")
    println("Enqueue: 4")
    queue.enqueue(4)
    println("Dequeue: ${queue.dequeue()}")
    println("Dequeue: ${queue.dequeue()}")

    val stack = stackOf("a", "b", "c")
    println("Stack: $stack")

    println("Pop: ${stack.pop()}")
    println("Pop: ${stack.pop()}")
    println("Push: d")
    stack.push("d")
    println("Pop: ${stack.pop()}")
    println("Pop: ${stack.pop()}")
    println("Pop: ${stack.pop()}")
}