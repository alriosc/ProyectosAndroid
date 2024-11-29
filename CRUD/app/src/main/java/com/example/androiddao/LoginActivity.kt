package com.example.androiddao

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    lateinit var usuarioDAO: UsuarioDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        // Referencias a los campos de entrada y botón
        val usernameEditText: EditText = findViewById(R.id.edit_usuario)
        val passwordEditText: EditText = findViewById(R.id.edit_contrasena)
        val loginButton: Button = findViewById(R.id.btn_login)

        // Instancia de la base de datos y UsuarioDAO
        val dbHelper = DBHelper(this)
        usuarioDAO = UsuarioDAO(dbHelper)

        // Acción al hacer clic en el botón de login
        loginButton.setOnClickListener {
            val usuarioIngresado = usernameEditText.text.toString().trim()
            val contrasenaIngresada = passwordEditText.text.toString().trim()

            // Validación básica
            if (usuarioIngresado.isEmpty() || contrasenaIngresada.isEmpty()) {
                Toast.makeText(this, "Por favor, ingrese su usuario y contraseña", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Verificar si las credenciales son locales (admin, cliente, productor)
            when {
                usuarioIngresado == "admin" && contrasenaIngresada == "123" -> {
                    // Redirigir a la actividad del Administrador
                    val intent = Intent(this, AdministradorActivity::class.java)
                    startActivity(intent)
                }
                usuarioIngresado == "cliente" && contrasenaIngresada == "123" -> {
                    // Redirigir a la actividad del Cliente
                    val intent = Intent(this, ClienteActivity::class.java)
                    startActivity(intent)
                }
                usuarioIngresado == "productor" && contrasenaIngresada == "123" -> {
                    // Redirigir a la actividad del Productor
                    val intent = Intent(this, ProductorActivity::class.java)
                    startActivity(intent)
                }
                else -> {
                    // Si no es uno de los usuarios locales, buscar en la base de datos
                    val usuario = usuarioDAO.obtenerUsuarioPorCredenciales(usuarioIngresado, contrasenaIngresada)
                    if (usuario != null) {
                        // Verificar el rol del usuario obtenido de la base de datos
                        when (usuario.rol) {
                            "admin" -> {
                                val intent = Intent(this, AdministradorActivity::class.java)
                                startActivity(intent)
                            }
                            "cliente" -> {
                                val intent = Intent(this, ClienteActivity::class.java)
                                startActivity(intent)
                            }
                            "productor" -> {
                                val intent = Intent(this, ProductorActivity::class.java)
                                startActivity(intent)
                            }
                            else -> {
                                Toast.makeText(this, "Rol desconocido", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        // Mostrar mensaje de error si las credenciales no son válidas
                        Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
