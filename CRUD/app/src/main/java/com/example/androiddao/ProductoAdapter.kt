package com.example.androiddao

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.BaseAdapter

class ProductoAdapter(private val context: Context, private var productos: List<Producto>) : BaseAdapter() {

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
        val rowView = convertView ?: inflater.inflate(android.R.layout.simple_list_item_2, parent, false)

        val nombreProductoTextView = rowView.findViewById<TextView>(android.R.id.text1)
        val precioProductoTextView = rowView.findViewById<TextView>(android.R.id.text2)

        val producto = productos[position]

        nombreProductoTextView.text = producto.nombre
        precioProductoTextView.text = "Precio: ${producto.precio} | Stock: ${producto.stock}"

        return rowView
    }

    fun actualizarProductos(nuevosProductos: List<Producto>) {
        this.productos = nuevosProductos
        notifyDataSetChanged()
    }
}
