package com.example.spinner_suma

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.spinner_suma.databinding.ActivitySegunda2Binding

class SegundaActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivitySegunda2Binding
    private var bundle: Bundle = Bundle()
    private lateinit var datoRecuperado: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySegunda2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        inicializacion()
        acciones()

    }

    private fun inicializacion() {
        datoRecuperado = intent.extras?.getDouble("dato").toString() ?: "Sin datos"
    }
    private fun acciones() {
        binding.textResultado.text = datoRecuperado
    }
}