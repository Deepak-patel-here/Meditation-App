package com.deepakjetpackcompose.nosleep.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.deepakjetpackcompose.nosleep.model.Recent
import com.deepakjetpackcompose.nosleep.model.User
import com.deepakjetpackcompose.nosleep.model.constants.USER
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore= FirebaseFirestore.getInstance()
    private val _authState = MutableStateFlow<AuthState>(AuthState.UnAuthenticated)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    private val _isLoading = MutableStateFlow<AuthState>(AuthState.notLoading)
    val isLoading: StateFlow<AuthState> = _isLoading

    private val _name= MutableStateFlow<String>("")
    val name: StateFlow<String> = _name.asStateFlow()

    private val _email = MutableStateFlow<String>("")
    val email: StateFlow<String> = _email.asStateFlow()


    init {
        checkAuthenticated()
    }

    fun checkAuthenticated() {
        if (auth.currentUser != null) {
            _authState.value = AuthState.Authenticated
        } else _authState.value = AuthState.UnAuthenticated
    }


    fun register(name :String,email: String, password: String,onResult:(Boolean,String?)-> Unit) {
        _isLoading.value = AuthState.Loading
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                onResult(true,"Registered successfully")
                _name.value=name
                _email.value=email
                _authState.value = AuthState.Authenticated
                _isLoading.value = AuthState.notLoading
            }
            .addOnFailureListener {
                onResult(false,it.localizedMessage)
                _authState.value = AuthState.UnAuthenticated
                _isLoading.value = AuthState.notLoading
            }



    }


    fun login(email: String, password: String,onResult:(Boolean,String?)-> Unit) {
        _isLoading.value = AuthState.Loading

        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                onResult(true,"Login Successfully")
                getUserData { user ->
                    if (user != null) {
                        _name.value = user.name
                        _email.value = user.email
                    }
                }
                _authState.value = AuthState.Authenticated
                _isLoading.value = AuthState.notLoading
            }
            .addOnFailureListener {
                onResult(false,it.localizedMessage)
                _authState.value = AuthState.UnAuthenticated
                _isLoading.value = AuthState.notLoading
            }

    }

    fun signOut() {
        _authState.value = AuthState.UnAuthenticated
    }

    fun saveUserToFireStore(user: User, onResult: (Boolean, String?) -> Unit) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid
        _isLoading.value= AuthState.Loading

        if (userId != null) {
            firestore
                .collection(USER)
                .document(userId)
                .set(user)
                .addOnSuccessListener {
                    onResult(true, "User saved successfully")
                    _isLoading.value= AuthState.notLoading
                }
                .addOnFailureListener {
                    onResult(false, it.localizedMessage)
                    _isLoading.value= AuthState.notLoading
                }
        } else {
            onResult(false, "User not logged in")
            _isLoading.value= AuthState.notLoading
        }
    }


    fun getUserData(callback: (User?) -> Unit) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid
        if(userId!=null) {
            firestore.collection(USER).document(userId).get()
                .addOnSuccessListener { item ->
                    val myUser = User(
                        name = item.getString("name") ?: "",
                        email = item.getString("email") ?: "",
                        preferences = item.get("preferences") as? List<String> ?: emptyList()
                    )
                    callback(myUser)
                }
                .addOnFailureListener {
                    callback(null)
                }
        }
    }
    fun fetchRecentAudio(onResult: (List<Recent>)-> Unit){
        firestore.collection(USER).document("Audios").collection("recent").get()
            .addOnSuccessListener { documents->
                val mediaList = documents.mapNotNull { it.toObject(Recent::class.java) }
                onResult(mediaList)
            }
            .addOnFailureListener {
                onResult(emptyList())
            }

    }
}

sealed class AuthState() {
    object Authenticated : AuthState()
    object UnAuthenticated : AuthState()
    object Loading : AuthState()
    object notLoading : AuthState()
}