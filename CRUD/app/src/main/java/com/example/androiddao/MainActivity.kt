package com.example.androiddao
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var productoDAO: ProductoDAO
    lateinit var adapter: ProductoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbHelper = DBHelper(this)
        productoDAO = ProductoDAO(dbHelper)

        // Definir los EditText para los datos del producto
        val nombreProductoEditText: EditText = findViewById(R.id.edit_nombre_producto)
        val precioProductoEditText: EditText = findViewById(R.id.edit_precio_producto)
        val stockProductoEditText: EditText = findViewById(R.id.edit_stock_producto)
        val descripcionProductoEditText: EditText = findViewById(R.id.edit_descripcion_producto)  // EditText para la descripción

        // Botones de la interfaz
        val agregarProductoButton: Button = findViewById(R.id.btn_agregar_producto)
        val actualizarProductoButton: Button = findViewById(R.id.btn_actualizar_producto)
        val eliminarProductoButton: Button = findViewById(R.id.btn_eliminar_producto)

        // ListView para mostrar los productos
        val listaProductos: ListView = findViewById(R.id.lista_productos)

        // Adaptador para mostrar productos
        adapter = ProductoAdapter(this, productoDAO.obtenerTodosLosProductos())
        listaProductos.adapter = adapter

        // Agregar producto
        agregarProductoButton.setOnClickListener {
            val nuevoProducto = Producto(
                nombre = nombreProductoEditText.text.toString(),
                precio = precioProductoEditText.text.toString().toDouble(),
                stock = stockProductoEditText.text.toString().toInt(),
                descripcion = descripcionProductoEditText.text.toString()  // Pasar la descripción
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
                descripcion = descripcionProductoEditText.text.toString()  // Pasar la descripción
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

