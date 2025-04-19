import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.ecommerceapp.viewmodel.AuthViewModel
import com.example.ecommerceapp.viewmodel.AuthState
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import org.junit.Assert.assertEquals

@RunWith(AndroidJUnit4::class)
class LogoutInstrumentedTest {

    @Test
    fun logout_setsAuthStateToUnauthenticated() {
        val latch = CountDownLatch(1)
        var currentState: AuthState? = null
        val authViewModel = AuthViewModel()

        // Observe on main thread
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            authViewModel.authState.observeForever { state ->
                currentState = state
                if (state == AuthState.Unauthenticated) {
                    latch.countDown()
                }
            }
        }

        // Call logout
        authViewModel.signout()

        latch.await(5, TimeUnit.SECONDS)
        assertEquals(AuthState.Unauthenticated, currentState)
    }
}