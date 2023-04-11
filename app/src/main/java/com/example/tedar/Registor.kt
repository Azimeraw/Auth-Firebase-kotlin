package com.example.tedar
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tedar.databinding.ActivityRegistorBinding
//import com.example.tedar.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class Registor : AppCompatActivity() {

    private lateinit var binding: ActivityRegistorBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.signupButton.setOnClickListener{
            val email = binding.signupEmail.text.toString()
            val password = binding.signupPassword.text.toString()
            val confirmPassword = binding.signupConfirm.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()){
                if (password == confirmPassword){

                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
                        if (it.isSuccessful){
                            val intent = Intent(this, Login::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Password does not matched", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
        binding.loginRedirectText.setOnClickListener {
            val loginIntent = Intent(this, Login::class.java)
            startActivity(loginIntent)
        }
    }
}