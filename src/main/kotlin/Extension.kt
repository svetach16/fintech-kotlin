import kotlin.math.sqrt

fun Int.squareRoot(): Int {
    val sqrt = sqrt(this.toDouble()).toInt()

    return when {
        sqrt * sqrt == this -> sqrt
        else -> throw IllegalArgumentException("$this is not a square")
    }
}