package com.example.androiddao

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AdministradorActivity : AppCompatActivity() {

    lateinit var usuarioDAO: UsuarioDAO
    lateinit var adapter: UsuarioAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_administrador)

        val dbHelper = DBHelper(this)
        usuarioDAO = UsuarioDAO(dbHelper)

        // Definir los EditText para los datos del usuario
        val nombreEditText: EditText = findViewById(R.id.edit_nombre_usuario)
        val apellidosEditText: EditText = findViewById(R.id.edit_apellidos_usuario)
        val usuarioEditText: EditText = findViewById(R.id.edit_usuario)
        val contrasenaEditText: EditText = findViewById(R.id.edit_contrasena_usuario)
        val rolEditText: EditText = findViewById(R.id.edit_rol_usuario)

        // Botones para agregar, actualizar y eliminar usuarios
        val agregarButton: Button = findViewById(R.id.btn_agregar_usuario)
        val actualizarButton: Button = findViewById(R.id.btn_actualizar_usuario)
        val eliminarButton: Button = findViewById(R.id.btn_eliminar_usuario)

        // ListView para mostrar los usuarios
        val listaUsuarios: ListView = findViewById(R.id.lista_usuarios)

        // Adaptador para la lista de usuarios
        adapter = UsuarioAdapter(this, usuarioDAO.obtenerTodosLosUsuarios())
        listaUsuarios.adapter = adapter

        // Acción para agregar un usuario
        agregarButton.setOnClickListener {
            val nuevoUsuario = Usuario(
                nombre = nombreEditText.text.toString(),
                apellidos = apellidosEditText.text.toString(),
                usuario = usuarioEditText.text.toString(),
                contraseña = contrasenaEditText.text.toString(),
                rol = rolEditText.text.toString()  // Rol del usuario (admin, cliente, productor)
            )

            if (usuarioDAO.insertarUsuario(nuevoUsuario)) {
                adapter.actualizarUsuarios(usuarioDAO.obtenerTodosLosUsuarios())
                Toast.makeText(this, "Usuario agregado", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error al agregar usuario", Toast.LENGTH_SHORT).show()
            }
        }

        // Acción para actualizar un usuario
        actualizarButton.setOnClickListener {
            val usuarioActualizado = Usuario(
                nombre = nombreEditText.text.toString(),
                apellidos = apellidosEditText.text.toString(),
                usuario = usuarioEditText.text.toString(),
                contraseña = contrasenaEditText.text.toString(),
                rol = rolEditText.text.toString()
            )

            if (usuarioDAO.actualizarUsuario(usuarioActualizado)) {
                adapter.actualizarUsuarios(usuarioDAO.obtenerTodosLosUsuarios())
                Toast.makeText(this, "Usuario actualizado", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error al actualizar usuario", Toast.LENGTH_SHORT).show()
            }
        }

        // Acción para eliminar un usuario
        eliminarButton.setOnClickListener {
            val usuario = usuarioEditText.text.toString()

            if (usuarioDAO.eliminarUsuarioPorNombre(usuario)) {
                adapter.actualizarUsuarios(usuarioDAO.obtenerTodosLosUsuarios())
                Toast.makeText(this, "Usuario eliminado", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error al eliminar usuario", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
