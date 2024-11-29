package com.example.loginpractica

// MainActivity.kt

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referencia al botón de Logout
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        // Agregar funcionalidad al botón de Logout
        btnLogout.setOnClickListener {
            // Crear un Intent para volver a LoginActivity
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
            // Finalizar la MainActivity para que no esté en la pila de actividades
            finish()
        }
    }
}
