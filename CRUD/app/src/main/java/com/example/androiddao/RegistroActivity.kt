package com.example.androiddao

import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.regex.Pattern

class RegistroActivity : AppCompatActivity() {

    lateinit var usuarioDAO: UsuarioDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val dbHelper = DBHelper(this)
        usuarioDAO = UsuarioDAO(dbHelper)

        val nombreEditText: EditText = findViewById(R.id.edit_nombre_registro)
        val apellidosEditText: EditText = findViewById(R.id.edit_apellidos_registro)
        val usuarioEditText: EditText = findViewById(R.id.edit_usuario_registro)
        val contrasenaEditText: EditText = findViewById(R.id.edit_contrasena_registro)
        val registrarButton: Button = findViewById(R.id.btn_registrar_usuario)

        registrarButton.setOnClickListener {
            val nombre = nombreEditText.text.toString()
            val apellidos = apellidosEditText.text.toString()
            val usuario = usuarioEditText.text.toString()
            val contrasena = contrasenaEditText.text.toString()

            if (!validarContrasena(contrasena)) {
                Toast.makeText(this, "La contraseña debe ser alfanumérica, entre 6 y 10 caracteres, con al menos una mayúscula, una minúscula y un carácter especial", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val nuevoUsuario = Usuario(
                nombre = nombreEditText.text.toString(),
                apellidos = apellidosEditText.text.toString(),
                usuario = usuarioEditText.text.toString(),
                contraseña = contrasenaEditText.text.toString(),
                rol = "cliente"  // Se asigna directamente el rol de cliente
            )


            if (usuarioDAO.insertarUsuario(nuevoUsuario)) {
                Toast.makeText(this, "Usuario registrado", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Error al registrar usuario", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Método para validar la contraseña
    private fun validarContrasena(contrasena: String): Boolean {
        val patron = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#\$%^&+=]).{6,10}\$")
        return patron.matcher(contrasena).matches()
    }
}
