import Human.HumanBuilder
import java.time.LocalDate

data class Human(
    val name: String,
    val age: Int
) {
    fun isOlderThan(age: Int) = this.age >= age

    fun drinkMilk(expirationDate: LocalDate) {
        when {
            LocalDate.now().isBefore(expirationDate) -> Unit
            else -> throw IllegalArgumentException("Milk gone bad")
        }
    }

    class HumanBuilder(
        var name: String = "",
        var age: Int = 0
    ) {
        fun build() = Human(name, age)
    }
}

fun human(block: HumanBuilder.() -> Unit): Human = HumanBuilder().apply { block() }.build()