import com.google.gson.Gson
import io.mockk.every
import io.mockk.spyk
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

class ClientServiceTest {

    private val gson = Gson()
    private val clientService = ClientService()
    private val client = spyk(getClientFromJson("/success/user.json"))

    @Test
    fun `success save client`() {
        val client = getClientFromJson("/success/user.json")
        val result = clientService.saveClient(client)
        assertNotNull(result)
    }

    @Test
    fun `fail save client - validation error`() {
        val client = getClientFromJson("/fail/user_with_bad_phone.json")
        assertThrows<ValidationException>("Errors expected") {
            clientService.saveClient(client)
        }
    }

    @Test
    fun `fail save client - validation error EMPTY_VALUE`() {
        every { client.firstName } returns null

        val exception = assertFailsWith<ValidationException>("EMPTY_VALUE error code expected") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.EMPTY_VALUE)
    }

    @Test
    fun `fail save client - validation error INVALID_CHARACTER`() {
        every { client.lastName } returns "平沢"

        val exception = assertFailsWith<ValidationException>("INVALID_CHARACTER error code expected") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_CHARACTER)
    }

    @Test
    fun `fail save client - validation error INVALID_LENGTH`() {
        every { client.lastName } returns "Вольфшлегельштайнхаузенбергедорф"

        val exception = assertFailsWith<ValidationException>("INVALID_LENGTH error code expected") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_LENGTH)
    }

    @Test
    fun `fail save client - validation error INVALID_PHONE_NUMBER`() {
        every { client.phone } returns "19057125358"

        val exception = assertFailsWith<ValidationException>("INVALID_PHONE_NUMBER error code expected") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_PHONE_NUMBER)
    }

    @Test
    fun `fail save client - validation error INVALID_EMAIL`() {
        every { client.email } returns "wrong@email"

        val exception = assertFailsWith<ValidationException>("INVALID_EMAIL error code expected") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_EMAIL)
    }

    @Test
    fun `fail save client - validation error INVALID_SNILS`() {
        every { client.snils } returns "56553713000"

        val exception = assertFailsWith<ValidationException>("INVALID_SNILS error code expected") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_SNILS)
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}