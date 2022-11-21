package com.jdr.chatcifrado.view.login


import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                    login() {
                        onClickGoogle()
                    }
                }
            }

        }

    }

    private val result =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { res: ActivityResult ->

            val task = GoogleSignIn.getSignedInAccountFromIntent(res.data)
            try {
                val account = task.getResult(ApiException::class.java)

                if (account != null) {
                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                    FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                Toast.makeText(this, "Se logueo", Toast.LENGTH_LONG).show()
                            } else {
                                Toast.makeText(this, "Error FirebaseAuth", Toast.LENGTH_LONG).show()
                            }
                        }
                } else {
                    Toast.makeText(this, "Error accounts", Toast.LENGTH_LONG).show()
                }

            } catch (ex: ApiException) {
                //Toast.makeText(this, "Error ApiException", Toast.LENGTH_LONG).show()
                Toast.makeText(this, "Error " + ex.message, Toast.LENGTH_LONG).show()
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
fun login(onClickGoogle: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Inicio de sesion")
        Spacer(modifier = Modifier.height(15.dp))
        Button(
            onClick = { onClickGoogle() },
            modifier = Modifier
                .width(150.dp)
                .fillMaxWidth()
                .shadow(0.dp),
            shape = RoundedCornerShape(28.dp),
            contentPadding = PaddingValues(15.dp),
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.white))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterStart)
                ) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.icon_google),
                        modifier = Modifier
                            .size(18.dp),
                        contentDescription = "drawable_icons",
                        tint = Color.Unspecified
                    )
                }
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "Log in",
                    color = colorResource(id = R.color.black),
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun DefaultPreview() {
    login() {

    }
}