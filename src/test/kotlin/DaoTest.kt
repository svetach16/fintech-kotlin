import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DaoTest {
    private val humanDao = mockk<HumanDao>()

    @BeforeEach
    fun initialize() {
        every { humanDao.getHuman(any()) } returns null
        every { humanDao.getHuman(1) } returns Human("Andrey Breslav", 36)
        every { humanDao.getHumans() } returns listOf(Human("Andrey Breslav", 36))
    }

    @Test
    fun `Human not found`() {
        assertNull(humanDao.getHuman(10))
    }

    @Test
    fun `Human found`() {
        val human = humanDao.getHuman(1)!!

        assertEquals("Andrey Breslav", human.name)
        assertEquals(36, human.age)
    }

    @Test
    fun `Get all humans`() {
        val humans = humanDao.getHumans()

        assertEquals(1, humans.size)

        val human = humans.single()
        assertEquals("Andrey Breslav", human.name)
        assertEquals(36, human.age)
    }
}