import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException
import java.time.LocalDate

class HumanTest {
    @Test
    fun `Human is adult`() {
        Assertions.assertTrue(Human("John", 20).isOlderThan(18))
    }

    @Test
    fun `Human is child`() {
        Assertions.assertFalse(Human("Annie", 10).isOlderThan(18))
    }

    @Test
    fun `Milk gone bad`() {
        assertThrows<IllegalArgumentException> {
            Human("John", 20).drinkMilk(expirationDate = LocalDate.now().minusDays(1))
        }
    }

    @Test
    fun `Milk is ok`() {
        Human("John", 20).drinkMilk(expirationDate = LocalDate.now().plusDays(1))
    }
}