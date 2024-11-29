package com.example.androiddao
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class UsuarioDAO(private val dbHelper: DBHelper) {

    // Insertar un nuevo usuario en la base de datos
    fun insertarUsuario(usuario: Usuario): Boolean {
        val db = dbHelper.writableDatabase
        val contentValues = ContentValues().apply {
            put("usuario", usuario.usuario)
            put("contraseña", usuario.contraseña)
            put("nombre", usuario.nombre)
            put("apellidos", usuario.apellidos)
            put("rol", usuario.rol)
        }
        val result = db.insert("usuarios", null, contentValues)
        return result != -1L
    }

    // Insertar usuarios predeterminados (Administrador, Cliente, Productor)
    fun insertarUsuariosPredeterminados() {
        val db = dbHelper.writableDatabase

        // Verificar si el administrador ya está insertado
        val cursor = db.rawQuery("SELECT * FROM usuarios WHERE usuario = ?", arrayOf("admin"))
        if (!cursor.moveToFirst()) {
            // Insertar Administrador
            val adminContentValues = ContentValues().apply {
                put("usuario", "admin")
                put("contraseña", "123")
                put("nombre", "Administrador")
                put("apellidos", "User")
                put("rol", "admin")
            }
            db.insert("usuarios", null, adminContentValues)
        }
        cursor.close()

        // Verificar si el cliente ya está insertado
        val cursorCliente = db.rawQuery("SELECT * FROM usuarios WHERE usuario = ?", arrayOf("cliente"))
        if (!cursorCliente.moveToFirst()) {
            // Insertar Cliente
            val clienteContentValues = ContentValues().apply {
                put("usuario", "cliente")
                put("contraseña", "123")
                put("nombre", "Cliente")
                put("apellidos", "User")
                put("rol", "cliente")
            }
            db.insert("usuarios", null, clienteContentValues)
        }
        cursorCliente.close()

        // Verificar si el productor ya está insertado
        val cursorProductor = db.rawQuery("SELECT * FROM usuarios WHERE usuario = ?", arrayOf("productor"))
        if (!cursorProductor.moveToFirst()) {
            // Insertar Productor
            val productorContentValues = ContentValues().apply {
                put("usuario", "productor")
                put("contraseña", "123")
                put("nombre", "Productor")
                put("apellidos", "User")
                put("rol", "productor")
            }
            db.insert("usuarios", null, productorContentValues)
        }
        cursorProductor.close()
    }


    // Obtener todos los usuarios
    fun obtenerTodosLosUsuarios(): List<Usuario> {
        val db = dbHelper.readableDatabase
        val listaUsuarios = mutableListOf<Usuario>()
        val cursor: Cursor = db.rawQuery("SELECT * FROM usuarios", null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                val nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"))
                val apellidos = cursor.getString(cursor.getColumnIndexOrThrow("apellidos"))
                val usuario = cursor.getString(cursor.getColumnIndexOrThrow("usuario"))
                val contraseña = cursor.getString(cursor.getColumnIndexOrThrow("contraseña"))
                val rol = cursor.getString(cursor.getColumnIndexOrThrow("rol"))
                listaUsuarios.add(Usuario(id, nombre, apellidos, usuario, contraseña, rol))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return listaUsuarios
    }

    // Obtener un usuario por credenciales (login)
    fun obtenerUsuarioPorCredenciales(usuario: String, contrasena: String): Usuario? {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.rawQuery(
            "SELECT * FROM usuarios WHERE usuario = ? AND contraseña = ?",
            arrayOf(usuario, contrasena)
        )

        return if (cursor.moveToFirst()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
            val nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"))
            val apellidos = cursor.getString(cursor.getColumnIndexOrThrow("apellidos"))
            val rol = cursor.getString(cursor.getColumnIndexOrThrow("rol"))
            cursor.close()
            Usuario(id, nombre, apellidos, usuario, contrasena, rol)
        } else {
            cursor.close()
            null
        }
    }


    // Actualizar un usuario existente
    fun actualizarUsuario(usuario: Usuario): Boolean {
        val db = dbHelper.writableDatabase
        val contentValues = ContentValues().apply {
            put("nombre", usuario.nombre)
            put("apellidos", usuario.apellidos)
            put("usuario", usuario.usuario)
            put("contraseña", usuario.contraseña)
            put("rol", usuario.rol)
        }
        val result = db.update("usuarios", contentValues, "id=?", arrayOf(usuario.id.toString()))
        return result > 0
    }

    // Eliminar un usuario por nombre de usuario
    fun eliminarUsuarioPorNombre(usuario: String): Boolean {
        val db = dbHelper.writableDatabase
        val result = db.delete("usuarios", "usuario=?", arrayOf(usuario))
        return result > 0
    }
}


