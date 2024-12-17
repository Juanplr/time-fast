package com.example.timefast.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.timefast.R
import com.example.timefast.poko.Envio

class EnviosAdapter(private var envios: List<Envio>, private val onItemClick: (Envio) -> Unit) :
    RecyclerView.Adapter<EnviosAdapter.EnvioViewHolder>() {

    class EnvioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvCliente: TextView = itemView.findViewById(R.id.tvNumeroGuia)
        val tvDestino: TextView = itemView.findViewById(R.id.tvDireccionDestino)
        val tvEstadoEnvio: TextView = itemView.findViewById(R.id.tvEstatus)
        val ivEstatus: ImageView = itemView.findViewById(R.id.ivEstatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EnvioViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_envio, parent, false)
        return EnvioViewHolder(view)
    }

    override fun onBindViewHolder(holder: EnvioViewHolder, position: Int) {
        val envio = envios[position]
        holder.tvCliente.text = envio.noGuia
        holder.tvDestino.text = envio.destino
        holder.tvEstadoEnvio.text = envio.estadoDeEnvio

        // Configura el icono según el estado
        when (envio.estadoDeEnvio) {
            "Entregado" -> holder.ivEstatus.setImageResource(R.drawable.ic_recibido)
            "En transito" -> holder.ivEstatus.setImageResource(R.drawable.ic_camino)
            "Pendiente" -> holder.ivEstatus.setImageResource(R.drawable.ic_pendiente)
            "Cancelado" -> holder.ivEstatus.setImageResource(R.drawable.ic_cancelado)
        }

        // Manejar el clic en el elemento
        holder.itemView.setOnClickListener {
            onItemClick(envio) // Ejecuta la función con el envío seleccionado
        }
    }

    override fun getItemCount(): Int = envios.size

    fun actualizarDatos(nuevosEnvios: List<Envio>) {
        this.envios = nuevosEnvios
        notifyDataSetChanged()
    }
}
