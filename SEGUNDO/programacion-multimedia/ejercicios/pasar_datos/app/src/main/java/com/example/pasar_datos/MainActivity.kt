package com.example.pasar_datos

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.View.OnClickListener
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pasar_datos.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var itemSeleccionado: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        inicializar()
        acciones()
    }

    private fun inicializar() {
        configurarSpinner()

    }



    private fun acciones() {
        binding.btAcceder.setOnClickListener(this)
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                itemSeleccionado = p0?.getItemAtPosition(p2).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }

    private fun configurarSpinner() {
        val adapter = ArrayAdapter.createFromResource(this,R.array.paises,android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            binding.btAcceder.id->{
                navegarSegundaActividad()
                Snackbar.make(binding.root,"El item seleccionado es ${binding.spinner.adapter.getItem(binding.spinner.selectedItemPosition)}",Snackbar.LENGTH_SHORT).show()

            }
        }
    }

    private fun navegarSegundaActividad() {
        var intent: Intent = Intent(applicationContext,SegundaActivity::class.java)
        var bundle: Bundle = Bundle()
        bundle.putString("nombre",binding.texto.text.toString())
        bundle.putString("otro",binding.otriDato.text.toString())
        bundle.putString("spinner",itemSeleccionado).toString()
        intent.putExtra("datos",bundle)
        // Retrasar el cambio de pantalla por 2 segundos
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(intent)
        },4000)
    }
}