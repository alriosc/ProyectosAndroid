package com.example.androiddao

import android.os.Bundle
import android.widget.Toast
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ClienteActivity : AppCompatActivity() {

    lateinit var productoDAO: ProductoDAO
    lateinit var adapter: ProductoAdapterCliente

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cliente)

        // Mostrar un mensaje de bienvenida para probar la actividad
        Toast.makeText(this, "Bienvenido a la vista del Cliente", Toast.LENGTH_SHORT).show()

        val dbHelper = DBHelper(this)
        productoDAO = ProductoDAO(dbHelper)

        val listaProductos: ListView = findViewById(R.id.lista_productos_cliente)

        // Adaptador para mostrar productos al cliente
        adapter = ProductoAdapterCliente(this, productoDAO.obtenerTodosLosProductos())
        listaProductos.adapter = adapter
    }
}
