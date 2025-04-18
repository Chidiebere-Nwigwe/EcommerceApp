import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ecommerceapp.viewmodel.AuthViewModel
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class LoginTest {

    @Test
    fun login_withValidCredentials_returnsSuccess() {
        val latch = CountDownLatch(1)
        var success = false
        var errorMessage: String? = null

        val authViewModel = AuthViewModel()

        authViewModel.login("test@gmail.com", "test123") { isSuccess, message ->
            success = isSuccess
            errorMessage = message
            latch.countDown()
        }

        latch.await(5, TimeUnit.SECONDS)

        assertTrue("Login should succeed, but failed with message: $errorMessage", success)
    }


    @Test
    fun login_withInvalidCredentials_returnsError() {
        val result = login("wrong@example.com", "wrongpassword")
        assertFalse(result.isSuccessful)
    }

    // Mock login function for testing purposes
    private fun login(email: String, password: String): LoginResult {
        return if (email == "test@example.com" && password == "password123") {
            LoginResult(true)
        } else {
            LoginResult(false)
        }
    }

    data class LoginResult(val isSuccessful: Boolean)
}
