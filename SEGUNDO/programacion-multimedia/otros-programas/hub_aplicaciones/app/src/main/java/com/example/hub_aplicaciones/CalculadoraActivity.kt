package com.example.hub_aplicaciones

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hub_aplicaciones.databinding.ActivityCalculadoraBinding

class CalculadoraActivity : AppCompatActivity(), OnItemSelectedListener {
    private lateinit var binding: ActivityCalculadoraBinding
    private lateinit var arrayItem: ArrayList<String>
    private lateinit var arrayAdapter: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculadoraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        instancias()

        //Listenners
        binding.btReultado.setOnClickListener{
            var operacion = binding.spinnerCalculadora.selectedItem.toString()
            var numero1: Int = binding.editText1.text.toString().toInt()
            var numero2: Int = binding.editText2.text.toString().toInt()
            var resultado: Int = 0
            when(operacion){
                "+"-> resultado = numero1+numero2
                "-"-> resultado = numero1 - numero2
                "*"-> resultado = numero1 * numero2
                "/"-> resultado = numero1 / numero2
            }
            var textoResultado = resultado.toString()
            var intent: Intent = Intent(applicationContext,ResultCalculadoraActivity::class.java)
            intent.putExtra("resultado",textoResultado)//String
            startActivity(intent)

        }
        //binding.spinnerCalculadora.onItemSelectedListener = this

    }

    private fun instancias() {
        arrayItem = arrayListOf("+","-","*","/")
        arrayAdapter = ArrayAdapter(applicationContext,android.R.layout.simple_spinner_item,arrayItem)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCalculadora.adapter = arrayAdapter
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}