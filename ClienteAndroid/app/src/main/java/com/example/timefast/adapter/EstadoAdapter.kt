package com.example.timefast.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.timefast.poko.EstadoEnvio

class EstadoAdapter (
    context: Context,
    private val estados: List<EstadoEnvio>
) : ArrayAdapter<EstadoEnvio>(context, android.R.layout.simple_spinner_item, estados) {

    init {
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return crearVista(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return crearVista(position, convertView, parent)
    }

    private fun crearVista(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(android.R.layout.simple_spinner_item, parent, false)

        val textView = view.findViewById<TextView>(android.R.id.text1)
        textView.text = estados[position].nombre

        return view
    }

}