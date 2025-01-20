package com.example.listas

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.listas.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), OnClickListener, OnItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var ciclos: ArrayList<CharSequence>
    private lateinit var adapterCiclos: ArrayAdapter<CharSequence>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        instancias()

        binding.btnEnviar.setOnClickListener(this)
        binding.spinnerHabilitar.onItemSelectedListener = this
        binding.spinnerCiclos.onItemSelectedListener = this
    }

    private fun instancias() {
        ciclos = arrayListOf("DAM", "DAW")
        //contexto → applicationContext
        //vista → vista que me da android: android.R.layout.simple_spinner_item
        //lista → lista de datos que quiero mostrar
        adapterCiclos = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, ciclos)
        adapterCiclos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCiclos.adapter = adapterCiclos
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.btnEnviar.id -> {
                val seleccionado: String = binding.spinnerOpciones.selectedItem.toString()
                Snackbar.make(
                    binding.root, "El seleccionado es ${seleccionado}", Snackbar.LENGTH_SHORT
                ).show()

                ciclos.add("ASIR")
                adapterCiclos.notifyDataSetChanged()
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when(parent?.id){
            binding.spinnerHabilitar.id->{
                val seleccion: String = parent.adapter.getItem(position).toString()
                Snackbar.make(binding.root, seleccion, Snackbar.LENGTH_SHORT).show()
                if(position == 0){
                    //habilita la pulsación del botón
                    binding.btnEnviar.isEnabled = true
                }else{
                    //dehabilita la pulsación del botón
                    binding.btnEnviar.isEnabled = false

                }
            }
            binding.spinnerCiclos.id-> {
                binding.textoCiclo.text = adapterCiclos.getItem(position)
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}