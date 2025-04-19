//package com.example.ecommerceapp
//
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import androidx.lifecycle.Observer
//import com.example.ecommerceapp.viewmodel.AuthState
//import com.example.ecommerceapp.viewmodel.AuthViewModel
//import com.google.firebase.auth.FirebaseAuth
//import org.junit.Assert.assertEquals
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.mockito.Mockito.*
//
//class AuthViewModelSignOutTest {
//
//    @get:Rule
//    val instantExecutorRule = InstantTaskExecutorRule()
//
//    private lateinit var auth: FirebaseAuth
//    private lateinit var viewModel: AuthViewModel
//
//    @Before
//    fun setup() {
//        auth = mock(FirebaseAuth::class.java) // ✅ no real Firebase
//        viewModel = AuthViewModel()
//    }
//
//    @Test
//    fun signout_updatesAuthStateAndCallsFirebaseSignOut() {
//        val observer = mock(Observer::class.java) as Observer<AuthState>
//        viewModel.authState.observeForever(observer)
//
//        viewModel.signout()
//
//        verify(auth).signOut()
//        assertEquals(AuthState.Unauthenticated, viewModel.authState.value)
//    }
//}
