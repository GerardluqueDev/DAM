package com.example.practica_login

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practica_login.databinding.ActivitySegunda2Binding

class SegundaActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivitySegunda2Binding
    private var bundle: Bundle = Bundle()
    private lateinit var nombre: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySegunda2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        recuperardDatos()
        acciones()

    }

    private fun acciones() {
        binding.textBienvenida.text = "Bienvenido de nuevo $nombre"
    }

    private fun recuperardDatos() {
        bundle = (intent.extras?.getBundle("datos") ?:"sin nombre") as Bundle
        nombre = bundle.getString("nombre").toString()
        bundle.getString("email")
    }
}