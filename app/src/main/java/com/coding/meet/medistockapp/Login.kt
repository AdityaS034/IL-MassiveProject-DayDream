package com.coding.meet.medistockapp

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.coding.meet.medistockapp.databinding.ActivityLoginBinding
import com.coding.meet.medistockapp.fragments.DashboardFragment

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            // Get user input
            val email = binding.edtEmail.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()

            // Make a request to the server for login
            performLogin(email, password)
        }

        binding.haventAccount.setOnClickListener {
            startActivity(Intent(this, Register::class.java))
        }

        etUsername = binding.edtEmail
        etPassword = binding.edtPassword
        setUser()
    }

    private fun setUser() {
        val bundle = intent.extras
        if (bundle != null) {
            etUsername.setText(bundle.getString("username"))
            etPassword.setText(bundle.getString("password"))
        }
    }

    private fun performLogin(email: String, password: String) {
        val volleyConnection = VolleyConnection(this)

        val request = object : StringRequest(
            Request.Method.POST,
            DbContract.SERVER_LOGIN_URL,
            Response.Listener { response ->
                // Handle the response from the server
                // Implement your logic here
                // For example, check if the login was successful
                if (response.contains("success")) {
                    // Login successful, set user as authenticated
                    setLoggedIn(true)

                    // Navigate to the main activity
                    startActivity(Intent(this, MainActivity::class.java))
                    finish() // Close the login activity
                } else {
                    // Login failed, show a toast or handle accordingly
                    Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
                }
            },
            Response.ErrorListener { error ->
                // Handle errors
                Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        ) {
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params["email"] = email
                params["password"] = password
                return params
            }
        }

        volleyConnection.addToRequestQueue(request)
    }

    // Function to set user authentication status
    private fun setLoggedIn(isLoggedIn: Boolean) {
        val sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("isLoggedIn", isLoggedIn)
        editor.apply()
    }
}
