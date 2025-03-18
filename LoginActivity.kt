package com.android.wrapitup // Replace with your actual package name

import LandingActivity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.wrapitup.SignUpActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val buttonRegister = findViewById<Button>(R.id.signupButton)
        buttonRegister.setOnClickListener {
            Log.e("Android Tutorial", "Register Button Pressed")
            val intent = Intent(this, SignUpActivity::class.java) // Replace with your signup activity
            startActivity(intent)
        }

        val editTextUsername = findViewById<EditText>(R.id.usernameEditText)
        val editTextPassword = findViewById<EditText>(R.id.passwordEditText)
        val buttonLogin = findViewById<Button>(R.id.loginButton)

        buttonLogin.setOnClickListener {
            val username = editTextUsername.text.toString()
            val password = editTextPassword.text.toString()

            if (username.isNullOrEmpty() || password.isNullOrEmpty()) {
                Toast.makeText(this, "Username or Password cannot be empty.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Log.d("LoginActivity", "Username: $username, Password: $password")

            if (username == "testuser" && password == "password") {
                storeUserSession(username)
                val intent = Intent(this, LandingActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        }


        if (isLoggedIn()) {
            val intent = Intent(this, LandingActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun storeUserSession(username: String) {
        val sharedPreferences: SharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("isLoggedIn", true)
        editor.putString("username", username)
        editor.apply()
    }

    private fun isLoggedIn(): Boolean {
        val sharedPreferences: SharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("isLoggedIn", false)
    }
}

class LogoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val builder = AlertDialog.Builder(this)
        builder.setMessage("Are you sure you want to log out?")
            .setCancelable(false)
            .setPositiveButton("Yes") { _, _ ->
                performLogout()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.cancel()
                finish()
            }
        val alert = builder.create()
        alert.show()
    }

    private fun performLogout() {
        clearUserSession()
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    private fun clearUserSession() {
        val sharedPreferences: SharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}