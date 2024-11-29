package com.example.androiddao

import android.content.ContentValues
import android.database.Cursor

class ProductoDAO(private val dbHelper: DBHelper) {

    // Insertar un nuevo producto en la base de datos
    fun insertarProducto(producto: Producto): Boolean {
        val db = dbHelper.writableDatabase
        val contentValues = ContentValues().apply {
            put("nombre", producto.nombre)
            put("precio", producto.precio)
            put("stock", producto.stock)
            put("descripcion", producto.descripcion)
        }
        val result = db.insert("productos", null, contentValues)
        return result != -1L
    }

    // Obtener todos los productos
    fun obtenerTodosLosProductos(): List<Producto> {
        val db = dbHelper.readableDatabase
        val listaProductos = mutableListOf<Producto>()
        val cursor: Cursor = db.rawQuery("SELECT * FROM productos", null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                val nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"))
                val precio = cursor.getDouble(cursor.getColumnIndexOrThrow("precio"))
                val stock = cursor.getInt(cursor.getColumnIndexOrThrow("stock"))
                val descripcion = cursor.getString(cursor.getColumnIndexOrThrow("descripcion"))
                listaProductos.add(Producto(id, nombre, precio, stock, descripcion))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return listaProductos
    }

    // Actualizar un producto
    fun actualizarProducto(producto: Producto): Boolean {
        val db = dbHelper.writableDatabase
        val contentValues = ContentValues().apply {
            put("nombre", producto.nombre)
            put("precio", producto.precio)
            put("stock", producto.stock)
            put("descripcion", producto.descripcion)
        }
        val result = db.update("productos", contentValues, "id=?", arrayOf(producto.id.toString()))
        return result > 0
    }

    // Eliminar un producto por ID
    fun eliminarProducto(id: Int): Boolean {
        val db = dbHelper.writableDatabase
        val result = db.delete("productos", "id=?", arrayOf(id.toString()))
        return result > 0
    }

    // Eliminar un producto por nombre
    fun eliminarProductoPorNombre(nombre: String): Boolean {
        val db = dbHelper.writableDatabase
        val result = db.delete("productos", "nombre=?", arrayOf(nombre))
        return result > 0
    }
}

