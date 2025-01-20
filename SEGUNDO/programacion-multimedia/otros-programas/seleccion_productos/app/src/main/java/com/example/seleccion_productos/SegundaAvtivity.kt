package com.example.seleccion_productos

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.seleccion_productos.databinding.ActivitySegundaAvtivityBinding

class SegundaAvtivity : AppCompatActivity() {
    private lateinit var binding: ActivitySegundaAvtivityBinding
    private var datosRecuperados: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySegundaAvtivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundelRecuperado: Bundle? = intent.extras?.getBundle("datos")
        var nombre = bundelRecuperado?.getString("nombreAnillo")
        var precio = bundelRecuperado?.getDouble("precioCarta")

        binding.textNombreCarta.text = nombre?:"No hay texto"
        binding.textPrecio.text = precio.toString()?:"No hay precio"

    }
}