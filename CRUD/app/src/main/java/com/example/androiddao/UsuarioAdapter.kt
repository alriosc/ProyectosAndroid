package com.example.androiddao

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.BaseAdapter

class UsuarioAdapter(private val context: Context, private var usuarios: List<Usuario>) : BaseAdapter() {

    override fun getCount(): Int {
        return usuarios.size
    }

    override fun getItem(position: Int): Any {
        return usuarios[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(context)
        val rowView = convertView ?: inflater.inflate(android.R.layout.simple_list_item_2, parent, false)

        val nombreTextView = rowView.findViewById<TextView>(android.R.id.text1)
        val usuarioTextView = rowView.findViewById<TextView>(android.R.id.text2)

        val usuario = usuarios[position]

        nombreTextView.text = usuario.nombre
        usuarioTextView.text = usuario.usuario

        return rowView
    }

    fun actualizarUsuarios(nuevosUsuarios: List<Usuario>) {
        this.usuarios = nuevosUsuarios
        notifyDataSetChanged()
    }
}
