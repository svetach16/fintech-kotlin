import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis

fun main() {
    val executors = mapOf(
        "Small" to Executors.newFixedThreadPool(10),
        "Medium" to Executors.newFixedThreadPool(20),
        "Large" to Executors.newFixedThreadPool(30)
    )

    val result = executors.mapValues { (_, executor) ->
        val i = AtomicInteger()

        val time = measureTimeMillis {
            (1..1_000_000).map { executor.submit { i.incrementAndGet() } }.forEach { it.get() }
        }

        time
    }

    result.entries.sortedBy { it.value }.forEach { println("${it.key}: ${it.value}") }

    executors.values.forEach { it.shutdown() }
}