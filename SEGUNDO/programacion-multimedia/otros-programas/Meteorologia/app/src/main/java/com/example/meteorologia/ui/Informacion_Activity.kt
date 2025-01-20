package com.example.meteorologia.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.meteorologia.R
import com.example.meteorologia.adapters.MetereoInfoAdapter
import com.example.meteorologia.databinding.ActivityInformacionBinding
import com.example.meteorologia.modelos.MetereoInfo
import com.google.android.material.snackbar.Snackbar

class Informacion_Activity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityInformacionBinding

    private lateinit var listaMeteoroliga: ArrayList<MetereoInfo>
    private lateinit var adapter: MetereoInfoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformacionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        instancias()
        acciones()

    }

    private fun acciones() {
        binding.btMostrar.setOnClickListener(this)
    }

    private fun instancias() {
        listaMeteoroliga = arrayListOf(
            MetereoInfo("23","Cualquier cosa pondrá aquí",R.drawable.ic_launcher_foreground)
        )
        adapter = MetereoInfoAdapter(listaMeteoroliga, this)
        binding.rvInformacion.layoutManager = LinearLayoutManager(this)
        binding.rvInformacion.adapter = adapter
    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            binding.btMostrar.id->{
                if(binding.editPoblacionInput.text.isNotEmpty()){
                    listaMeteoroliga.add(MetereoInfo("57","Seguimos con lo que sea",R.drawable.ic_launcher_foreground))
                    adapter.notifyDataSetChanged()
                    Log.d("DEBUG", "Lista actual: ${listaMeteoroliga.size}")
                }else{
                    Snackbar.make(binding.root,"Debes ingresar una poblacion",Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }
}