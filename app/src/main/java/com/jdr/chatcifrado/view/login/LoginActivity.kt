package com.jdr.chatcifrado.view.login

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.jdr.chatcifrado.R
import com.jdr.chatcifrado.ui.theme.ChatCifradoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {

    private val GOOGLE_SIGN_IN = 468

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContent {
            ChatCifradoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(onClickGoogle = {
                        onClickGoogle()
                    })
                }
            }

        }

    }

    val result =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { res: ActivityResult ->

            val task = GoogleSignIn.getSignedInAccountFromIntent(res.data)
            try {
                val account = task.getResult(ApiException::class.java)

                if(account != null){
                    val credencial = GoogleAuthProvider.getCredential(account.idToken, null)
                    FirebaseAuth.getInstance().signInWithCredential(credencial).addOnCompleteListener {
                        if(it.isSuccessful){
                            Toast.makeText(this, "Se logueo", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(this, "Error FirebaseAuth", Toast.LENGTH_LONG).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Error accounts", Toast.LENGTH_LONG).show()
                }

            } catch (ex: ApiException){
                //Toast.makeText(this, "Error ApiException", Toast.LENGTH_LONG).show()
                Toast.makeText(this, "Error "+ex.message, Toast.LENGTH_LONG).show()
            }


        }

    private fun onClickGoogle() {
        FirebaseAuth.getInstance().signOut()
        val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_client_id))
            .requestEmail()
            .build()

        val googleClient = GoogleSignIn.getClient(this, googleConf)
        googleClient.signOut()
        result.launch(googleClient.signInIntent)

    }

}


@Composable
fun Greeting(onClickGoogle: () -> Unit) {
    Button(
        onClick = {
            onClickGoogle()
        },
    ) {
        Text(text = "Google")
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ChatCifradoTheme {
        Greeting({})
    }
}