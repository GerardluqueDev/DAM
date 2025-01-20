package com.example.meteorologia.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.meteorologia.R
import com.example.meteorologia.modelos.MetereoInfo

class MetereoInfoAdapter(var lista: ArrayList<MetereoInfo>, var contexto: Context) : RecyclerView.Adapter<MetereoInfoAdapter.MyHolder>() {
    class MyHolder(var itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagen = itemView.findViewById<ImageView>(R.id.img_logo)
        val temperatura = itemView.findViewById<TextView>(R.id.textViewTemperatura)
        val descripcion = itemView.findViewById<TextView>(R.id.textViewInfo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MetereoInfoAdapter.MyHolder {
        val vista = LayoutInflater.from(contexto).inflate(R.layout.item_informacion, parent, false)
        val holder = MyHolder(vista)
        return holder
    }

    override fun onBindViewHolder(holder: MetereoInfoAdapter.MyHolder, position: Int) {
        val metereoInfo = lista[position]
        holder.imagen.setImageResource(metereoInfo.icono)
        holder.temperatura.text = metereoInfo.temperatura
        holder.descripcion.text = metereoInfo.descripcion
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    fun actualizar(lista: ArrayList<MetereoInfo>) {
        this.lista = lista
        notifyDataSetChanged()
    }
}