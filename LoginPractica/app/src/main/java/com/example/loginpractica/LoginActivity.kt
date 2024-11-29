package com.example.loginpractica

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class LoginActivity : AppCompatActivity() {


    private var isPasswordVisible: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvError = findViewById<TextView>(R.id.tvError)
        val togglePasswordVisibilityButton = findViewById<ImageButton>(R.id.togglePasswordVisibilityButton)

        // Lógica para alternar la visibilidad de la contraseña
        togglePasswordVisibilityButton.setOnClickListener {
            if (isPasswordVisible) {
                // Ocultar contraseña
                password.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                togglePasswordVisibilityButton.setImageResource(R.drawable.ic_visibility_off) // Cambia al ícono de "ocultar"
            } else {
                // Mostrar contraseña
                password.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                togglePasswordVisibilityButton.setImageResource(R.drawable.ic_visibility) // Cambia al ícono de "mostrar"
            }
            // Mantener el cursor al final del texto
            password.setSelection(password.text.length)
            isPasswordVisible = !isPasswordVisible
        }

        btnLogin.setOnClickListener {
            val user = username.text.toString()
            val pass = password.text.toString()

            // Validación de credenciales
            if (user == "Angie" && pass == "73248750") {
                // Credenciales correctas, ir a la siguiente actividad
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            } else {
                // Credenciales incorrectas, mostrar mensaje
                tvError.visibility = View.VISIBLE
            }
        }
    }
}
