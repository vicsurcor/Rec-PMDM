package com.example.recuperacion_pmdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val editTextEmail: EditText = findViewById(R.id.editTextEmail)
        val editTextUsername: EditText = findViewById(R.id.editTextUsername)
        val editTextPassword: EditText = findViewById(R.id.editTextPassword)
        val buttonSignUp: Button = findViewById(R.id.buttonSignUp)

        buttonSignUp.setOnClickListener {
            val email = editTextEmail.text.toString().trim()
            val username = editTextUsername.text.toString().trim()
            val password = editTextPassword.text.toString().trim()

            // Add validation and sign-up logic here

            Toast.makeText(this, "Signed Up", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@SignUpActivity, MainMenuActivity::class.java)
            startActivity(intent)
        }
    }
}