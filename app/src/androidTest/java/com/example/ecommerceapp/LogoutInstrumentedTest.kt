import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.ecommerceapp.viewmodel.AuthViewModel
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import org.junit.Assert.assertEquals
import com.example.ecommerceapp.viewmodel.AuthState

@RunWith(AndroidJUnit4::class)
class LogoutInstrumentedTest {

    @Test
    fun logout_setsAuthStateToUnauthenticated() {
        val latch = CountDownLatch(1)
        var currentState: AuthState? = null

        val authViewModel = AuthViewModel()

        // Observe state changes
        authViewModel.authState.observeForever { state ->
            currentState = state
            if (state == AuthState.Unauthenticated) {
                latch.countDown()
            }
        }

        // Call logout
        authViewModel.signout()

        latch.await(5, TimeUnit.SECONDS)
        assertEquals(AuthState.Unauthenticated, currentState)
    }
}
