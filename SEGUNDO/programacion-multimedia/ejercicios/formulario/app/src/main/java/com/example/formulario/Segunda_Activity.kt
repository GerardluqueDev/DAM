package com.example.formulario

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.formulario.databinding.ActivitySegundaBinding

class Segunda_Activity : AppCompatActivity() {
    private lateinit var binding: ActivitySegundaBinding
    private lateinit var textoNombre: String
    private lateinit var textoApellido: String
    private lateinit var textoEmail: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySegundaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        capturarDatos()
        acciones()
    }

    private fun capturarDatos() {
        textoNombre = intent.extras?.getString("nombre").toString()
        textoApellido = intent.extras?.getString("apellido").toString()
        textoEmail = intent.extras?.getString("email").toString()
    }

    private fun acciones() {
        binding.textRecibidoNombre.text = textoNombre
        binding.textRecibidoApellido.text = textoApellido
        binding.textRecibidoEmail.text = textoEmail
    }
}