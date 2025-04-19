import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.ecommerceapp.viewmodel.AuthViewModel
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import org.junit.Assert.assertTrue

@RunWith(AndroidJUnit4::class)
class LoginInstrumentedTest {

    @Test
    fun login_withValidCredentials_returnsSuccess() {
        val latch = CountDownLatch(1)
        var success = false

        val authViewModel = AuthViewModel()

        authViewModel.login("test@gmail.com", "test123") { isSuccess, _ ->
            success = isSuccess
            latch.countDown()
        }

        latch.await(5, TimeUnit.SECONDS)
        assertTrue(success)
    }
}