package com.example.pasar_datos

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pasar_datos.databinding.ActivitySegundaBinding

class SegundaActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySegundaBinding
    private lateinit var texto: String
    private lateinit var otroDato: String
    private lateinit var spinner: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySegundaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        inicializar()

    }

    private fun inicializar() {
        var bundle: Bundle = Bundle()
        bundle = intent.extras?.getBundle("datos")!!
        texto = bundle.getString("nombre").toString()
        otroDato = bundle.getString("otro").toString()
        spinner = bundle.getString("spinner").toString()

        mostrarDatos(texto, otroDato)
    }

    private fun mostrarDatos(texto: String, otroDato: String) {
        binding.textRecibido.text = texto
        binding.otroRecibido.text = otroDato
        binding.seleccionSpinner.text = spinner
    }

}