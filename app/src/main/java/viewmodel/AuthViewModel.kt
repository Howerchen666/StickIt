package viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()

    val currentUser = mutableStateOf(auth.currentUser)

    fun signIn(email: String, password: String, onResult: (Boolean) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                currentUser.value = auth.currentUser
                onResult(task.isSuccessful)
            }
    }
    fun register(email: String, password: String, onResult: (Boolean) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                currentUser.value = auth.currentUser
                if (task.isSuccessful) {
                    Log.d("Auth", "Registration successful: ${auth.currentUser?.email}")
                    onResult(true)
                } else {
                    Log.e("Auth", "Registration failed", task.exception)
                    onResult(false)
                }
            }
    }    fun signOut() {
        auth.signOut()
        currentUser.value = null
    }
}