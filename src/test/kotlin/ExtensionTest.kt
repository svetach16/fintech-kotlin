import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ExtensionTest {
    @Test
    fun `36 is square`() {
        assertEquals(6, 36.squareRoot())
    }

    @Test
    fun `47 is not a square`() {
        assertThrows<IllegalArgumentException> { 37.squareRoot() }
    }
}