//package com.example.ecommerceapp.viewmodel
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import com.example.ecommerceapp.model.UserModel
//import com.google.firebase.Firebase
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.auth
//import com.google.firebase.firestore.firestore
//
//
//class AuthViewModel(    private val auth1: FirebaseAuth = FirebaseAuth.getInstance()
//) : ViewModel(){
//    // val auth that is from firebase auth
////    private val auth : FirebaseAuth = FirebaseAuth.getInstance()
//    private val auth = Firebase.auth
//
//    private val firestore = Firebase.firestore
//
//
//    private val _authState = MutableLiveData<AuthState>()
//    val authState: LiveData<AuthState> = _authState
//
//    init{
//        checkAuthStatus()
//    }
//
//    fun checkAuthStatus(){
//        if(auth.currentUser==null){
//            _authState.value = AuthState.Unauthenticated
//        }else{
//            _authState.value = AuthState.Authenticated
//        }
//    }
//
//    fun login(email: String, password: String, onResult: (Boolean,String?)-> Unit){
//        auth.signInWithEmailAndPassword(email,password)
//            .addOnCompleteListener(){ task->
//                if(task.isSuccessful){
//                    onResult(true, null)
//                }else{
//                    onResult(false, task.exception?.localizedMessage)
//                }
//            }
//    }
//
//    fun signup(email: String, name: String, password: String, onResult: (Boolean,String?)-> Unit){
//        auth.createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener{ task->
//                if(task.isSuccessful){
//                    var userId = task.result?.user?.uid
//                    val userModel = UserModel(name, email, userId!!)
//
//                    firestore.collection("users").document(userId)
//                        .set(userModel)
//                        .addOnCompleteListener{ dbTask ->
//                            if(dbTask.isSuccessful){
//                                onResult(true, null)
//                            }else{
//                                onResult(false, "Something went wrong")
//                            }
//                        }
//                    _authState.value = AuthState.Authenticated
//                }else{
//                    onResult(false, task.exception?.localizedMessage)
//                }
//            }
//    }
//
//    fun signout(){
//        auth.signOut()
//        _authState.value = AuthState.Unauthenticated
//    }
//}
//
////sealed class for ui
//sealed class AuthState{
//    object Authenticated : AuthState()
//    object Unauthenticated : AuthState()
//    object Loading : AuthState()
//    data class Error(val message : String) : AuthState()
//}

package com.example.ecommerceapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ecommerceapp.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.ktx.firestore

class AuthViewModel(
    private val auth1: FirebaseAuth = FirebaseAuth.getInstance()
) : ViewModel() {

    private val auth = Firebase.auth
    private val firestore = Firebase.firestore

    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    init {
        checkAuthStatus()
    }

    fun checkAuthStatus() {
        if (auth.currentUser == null) {
            _authState.postValue(AuthState.Unauthenticated)
        } else {
            _authState.postValue(AuthState.Authenticated)
        }
    }

    fun login(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true, null)
                } else {
                    onResult(false, task.exception?.localizedMessage)
                }
            }
    }

    fun signup(email: String, name: String, password: String, onResult: (Boolean, String?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = task.result?.user?.uid ?: return@addOnCompleteListener
                    val userModel = UserModel(name, email, userId)

                    firestore.collection("users").document(userId)
                        .set(userModel)
                        .addOnCompleteListener { dbTask ->
                            if (dbTask.isSuccessful) {
                                onResult(true, null)
                            } else {
                                onResult(false, "Something went wrong")
                            }
                        }

                    _authState.postValue(AuthState.Authenticated)
                } else {
                    onResult(false, task.exception?.localizedMessage)
                }
            }
    }

    fun signout() {
        auth.signOut()
        _authState.postValue(AuthState.Unauthenticated)
    }
}

// sealed class for UI state
sealed class AuthState {
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object Loading : AuthState()
    data class Error(val message: String) : AuthState()
}
