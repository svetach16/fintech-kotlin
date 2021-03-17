class Human(
    val id: Int,
    val age: Int,
    val name: String
) {
    override fun toString(): String = "Human(name='$name')"
}

class Children(
    val fatherId: Int,
    val motherId: Int,
    val childrenIds: List<Int>
)

data class Parent(
    val id: Int,
    val age: Int,
    val name: String,
    val childrenIds: List<Int>
)

object HumanDao {
    fun getHumans() = listOf(
        Human(1, 20, "Kiselev Vasily"),
        Human(2, 22, "Luzanova Olga"),
        Human(3, 21, "Lovchikova Natalia"),
        Human(4, 45, "Lovchikova Tatyana"),
        Human(5, 38, "Lovchikov Aleksandr"),
        Human(6, 15, "Liskova Masha"),
        Human(7, 49, "Liskova Marina"),
        Human(8, 31, "Laidus Tatyana"),
        Human(9, 54, "Liskov Ivan"),
        Human(10, 33, "Kereeva Valentina"),
        Human(11, 10, "Kereeva Sofia"),
        Human(12, 41, "Kereev Vladislav"),
        Human(13, 60, "Vinogradov Oleg"),
        Human(14, 55, "Vinogradova Arina"),
        Human(15, 32, "Vinogradov Artem"),
        Human(16, 34, "Vinogradov Evgeniy")
    )
}

object ChildrenDao {
    fun getChildren() = listOf(
        Children(fatherId = 5, motherId = 4, listOf(3)),
        Children(fatherId = 9, motherId = 7, listOf(6)),
        Children(fatherId = 12, motherId = 10, listOf(11)),
        Children(fatherId = 13, motherId = 14, listOf(16, 15))
    )

    fun getByParent(parentId: Int) = getChildren().find {
        it.fatherId == parentId || it.motherId == parentId
    }
}

object Service {
    fun getParents(): List<Parent> {
        val humans = HumanDao.getHumans()

        return humans.mapNotNull { human ->
            val children = ChildrenDao.getByParent(human.id)?.childrenIds

            children?.let {
                Parent(
                    human.id,
                    human.age,
                    human.name,
                    it
                )
            }
        }
    }

    fun getParentsSorted() = getParents().sortedBy { it.name }

    fun groupParentsByChild() = getParents().groupBy { it.childrenIds }

    fun filterParents(predicate: (Parent) -> Boolean) = getParents().filter(predicate).count()
}

fun main() {
    println("Parents:")
    Service.getParents().forEach(System.out::println)
    println("\nParents sorted by name: \n${Service.getParentsSorted().joinToString(separator = "\n", postfix = "\n")}")
    println("\nParents grouped by child:")
    Service.groupParentsByChild().forEach(System.out::println)
    println("\nNumber of parents with one child:")
    println(Service.filterParents { it.childrenIds.size == 1 })
}