import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DslTest {
    @Test
    fun `use mockk for dsl`() {
        val humanMockk = mockk<Human>()

        every { humanMockk.age } returns 36
        every { humanMockk.name } returns "Andrey Breslav"

        val human = human {
            name = "Andrey Breslav"
            age = 36
        }

        assertEquals(human, humanMockk)
    }
}