package com.example.androiddao

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ProductoAdapterCliente(private val context: Context, private var productos: List<Producto>) : BaseAdapter() {

    override fun getCount(): Int {
        return productos.size
    }

    override fun getItem(position: Int): Any {
        return productos[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(context)
        val rowView = convertView ?: inflater.inflate(R.layout.item_producto_cliente, parent, false)

        val nombreProductoTextView = rowView.findViewById<TextView>(R.id.text_nombre_producto)
        val precioProductoTextView = rowView.findViewById<TextView>(R.id.text_precio_producto)
        val stockProductoTextView = rowView.findViewById<TextView>(R.id.text_stock_producto)
        val descripcionProductoTextView = rowView.findViewById<TextView>(R.id.text_descripcion_producto)

        val producto = productos[position]

        nombreProductoTextView.text = producto.nombre
        precioProductoTextView.text = "Precio: ${producto.precio}"
        stockProductoTextView.text = "Stock: ${producto.stock}"
        descripcionProductoTextView.text = "Descripción: ${producto.descripcion}"

        return rowView
    }
}

