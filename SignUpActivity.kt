package com.example.wrapitup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.wrapitup.R

class SignUpActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var signupButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        usernameEditText = findViewById(R.id.usernameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText)
        signupButton = findViewById(R.id.signupButton)

        signupButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val confirmPassword = confirmPasswordEditText.text.toString().trim()

            when {
                username.isEmpty() -> {
                    usernameEditText.error = "Username is required"
                    usernameEditText.requestFocus()
                }
                password.isEmpty() -> {
                    passwordEditText.error = "Password is required"
                    passwordEditText.requestFocus()
                }
                confirmPassword.isEmpty() -> {
                    confirmPasswordEditText.error = "Confirm Password is required"
                    confirmPasswordEditText.requestFocus()
                }
                password != confirmPassword -> {
                    confirmPasswordEditText.error = "Passwords do not match"
                    confirmPasswordEditText.requestFocus()
                }
                else -> {
                    // Replace with your actual data storage logic
                    Toast.makeText(this, "Sign Up Successful", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}