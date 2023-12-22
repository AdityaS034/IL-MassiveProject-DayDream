package com.coding.meet.medistockapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.coding.meet.medistockapp.databinding.ActivityRegisterBinding

class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            // Get user input
            val firstName = binding.namaDepan.text.toString().trim()
            val lastName = binding.namaBelakang.text.toString().trim()
            val email = binding.regisEmail.text.toString().trim()
            val password = binding.kataSandi.text.toString().trim()
            val confirmPassword = binding.konfirmasiSandi.text.toString().trim()
            val phoneNumber = binding.nomorTelepon.text.toString().trim()

            // Make a request to the server for registration
            performRegistration(firstName, lastName, email, password, confirmPassword, phoneNumber)
        }
    }

    private fun performRegistration(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        confirmPassword: String,
        phoneNumber: String
    ) {
        val volleyConnection = VolleyConnection(this)

        val request = object : StringRequest(
            Request.Method.POST,
            DbContract.SERVER_REGISTER_URL,
            Response.Listener { response ->
                // Handle the response from the server
                // Implement your logic here
                // For example, check if the registration was successful
                if (response.contains("success")) {
                    // Registration successful, navigate to the login activity
                    startActivity(Intent(this, Login::class.java))
                } else {
                    // Registration failed, show a toast or handle accordingly
                    Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show()
                }
            },
            Response.ErrorListener { error ->
                // Handle errors
                Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        ) {
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params["firstname"] = firstName
                params["lastname"] = lastName
                params["email"] = email
                params["password"] = password
                params["confirm_password"] = confirmPassword
                params["phone_number"] = phoneNumber
                return params
            }
        }

        volleyConnection.addToRequestQueue(request)
    }
}
