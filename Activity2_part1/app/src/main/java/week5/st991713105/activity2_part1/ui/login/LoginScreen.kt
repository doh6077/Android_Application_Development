package week5.st991713105.activity2_part1.ui.login

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text

import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp
import com.google.firebase.Firebase
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

@Composable
fun LoginScreen(){


    val auth = Firebase.auth

    val emailState = remember {(mutableStateOf(""))}
    val passwordState = remember {(mutableStateOf(""))}

    Log.d("mylog", "the curren email:${auth.currentUser?.email}")
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        // field 1 - email
        TextField(value = emailState.value, onValueChange = {
            emailState.value = it
        })

        Spacer(Modifier.height(10.dp))
        // field 2 - password
        TextField(value = passwordState.value, onValueChange = {
            passwordState.value = it
        })
        Spacer(Modifier.height(10.dp))
        // button SignIn
        Button(
            onClick = ({
                signIn(auth, emailState.value, passwordState.value)
            })
        ){

            Text("Sign In")
        }
        Spacer(Modifier.height(10.dp))

        // button SignUp
        Button(
            onClick = ({
                signUp(auth, emailState.value, passwordState.value)
            })
        ){

            Text("Sign Up")
        }


        Spacer(Modifier.height(10.dp))

        // button Sign Out
        Button(
            onClick = ({
                signOutIn(auth )
                Log.d("mylog", "the curren email:${auth.currentUser?.email}")
            })
        ){

            Text("Sign Out")
        }

        Spacer(Modifier.height(10.dp))

        // button deleteAccount
        Button(
            onClick = ({
                deleteAccount(auth, emailState.value, passwordState.value)
            })
        ){

            Text("Delete Account")
        }
    }

}

private fun signUp(auth: FirebaseAuth, email: String, password: String) {
    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener {
            if (it.isSuccessful) {
                Log.d("mylog", "SignUp successful")
            } else {
                Log.d("mylog", "SignUp Not successful")
            }
        }
}

private fun signIn(auth: FirebaseAuth, email: String, password: String) {
    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener {
            if (it.isSuccessful) {
                Log.d("mylog", "Sign In successful")
            } else {
                Log.d("mylog", "Sign In Failed")
            }
        }
}

private fun signOutIn(auth: FirebaseAuth) {
    auth.signOut()
    Log.d("mylog", "Sign Out")
}

private fun deleteAccount(auth: FirebaseAuth, email: String, password: String) {
    val credential = EmailAuthProvider.getCredential(email,password)
    auth.currentUser?.reauthenticate(credential)?.addOnCompleteListener {it
        if (it.isSuccessful) {
            auth.currentUser?.delete()
            Log.d("mylog", "User was deleted")
        } else {
            Log.d("mylog", "reauthentication failed")
        }
    }
}