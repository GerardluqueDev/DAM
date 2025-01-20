package com.example.concesionario.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.concesionario.R
import com.example.concesionario.model.Modelo
import com.example.concesionario.ui.DetailActivity

class AdaptadorModelos(var lista: ArrayList<Modelo>, var contexto: Context): RecyclerView.Adapter<AdaptadorModelos.MyHolder>() {

    class MyHolder(itemView: View) : ViewHolder(itemView){
        // Saco cada uno de los elementos que hay dentro del XML (patron de la fila)
        val botono = itemView.findViewById<Button>(R.id.btModelo)
        val texto = itemView.findViewById<TextView>(R.id.textoModelo)
        val imagen = itemView.findViewById<ImageView>(R.id.imagenModelo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        // crea el patron de las filas
        val vista = LayoutInflater.from(contexto).inflate(R.layout.item_model, parent, false)
        val holder: MyHolder = MyHolder(vista)
        return holder
    }

    override fun getItemCount(): Int {
        // Me dice cuantas filas hay
        return  lista.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        // Me dice como se comporta cada fila
        val modelo = lista[position]
        holder.imagen.setImageResource(modelo.imagen)
        holder.texto.text = modelo.modelo
        // holder.botono -> Escuchar la pulsación del botón ver detalles
        holder.botono.setOnClickListener {
            val intent = Intent(contexto, DetailActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            val bundle = Bundle()
            bundle.putSerializable("coche", modelo)
            intent.putExtra("datos", bundle)
            contexto.startActivity(intent)
        }

    }
    public fun actualizarLista(listaModelos: ArrayList<Modelo>){
        this.lista = listaModelos
        notifyDataSetChanged()
    }

}