package com.example.androiddao

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProductorActivity : AppCompatActivity() {

    lateinit var productoDAO: ProductoDAO
    lateinit var adapter: ProductoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productor)
        // Mostrar un mensaje de bienvenida para probar la actividad
        Toast.makeText(this, "Bienvenido a la vista del Productor", Toast.LENGTH_SHORT).show()
        val dbHelper = DBHelper(this)
        productoDAO = ProductoDAO(dbHelper)

        // Definimos los EditText para obtener los datos de los productos
        val nombreProductoEditText: EditText = findViewById(R.id.edit_nombre_producto)
        val precioProductoEditText: EditText = findViewById(R.id.edit_precio_producto)
        val stockProductoEditText: EditText = findViewById(R.id.edit_stock_producto)
        val descripcionProductoEditText: EditText = findViewById(R.id.edit_descripcion_producto)  // Nuevo campo para la descripción

        val agregarProductoButton: Button = findViewById(R.id.btn_agregar_producto)
        val actualizarProductoButton: Button = findViewById(R.id.btn_actualizar_producto)
        val eliminarProductoButton: Button = findViewById(R.id.btn_eliminar_producto)

        val listaProductos: ListView = findViewById(R.id.lista_productos)

        // Adaptador para mostrar productos al productor
        adapter = ProductoAdapter(this, productoDAO.obtenerTodosLosProductos())
        listaProductos.adapter = adapter

        // Agregar producto
        agregarProductoButton.setOnClickListener {
            val nuevoProducto = Producto(
                nombre = nombreProductoEditText.text.toString(),
                precio = precioProductoEditText.text.toString().toDouble(),
                stock = stockProductoEditText.text.toString().toInt(),
                descripcion = descripcionProductoEditText.text.toString()  // Obtenemos la descripción
            )

            if (productoDAO.insertarProducto(nuevoProducto)) {
                adapter.actualizarProductos(productoDAO.obtenerTodosLosProductos())
                Toast.makeText(this, "Producto agregado", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error al agregar producto", Toast.LENGTH_SHORT).show()
            }
        }

        // Actualizar producto
        actualizarProductoButton.setOnClickListener {
            val productoActualizado = Producto(
                nombre = nombreProductoEditText.text.toString(),
                precio = precioProductoEditText.text.toString().toDouble(),
                stock = stockProductoEditText.text.toString().toInt(),
                descripcion = descripcionProductoEditText.text.toString()  // Obtenemos la descripción
            )

            if (productoDAO.actualizarProducto(productoActualizado)) {
                adapter.actualizarProductos(productoDAO.obtenerTodosLosProductos())
                Toast.makeText(this, "Producto actualizado", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error al actualizar producto", Toast.LENGTH_SHORT).show()
            }
        }

        // Eliminar producto
        eliminarProductoButton.setOnClickListener {
            val nombreProducto = nombreProductoEditText.text.toString()

            if (productoDAO.eliminarProductoPorNombre(nombreProducto)) {
                adapter.actualizarProductos(productoDAO.obtenerTodosLosProductos())
                Toast.makeText(this, "Producto eliminado", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error al eliminar producto", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

