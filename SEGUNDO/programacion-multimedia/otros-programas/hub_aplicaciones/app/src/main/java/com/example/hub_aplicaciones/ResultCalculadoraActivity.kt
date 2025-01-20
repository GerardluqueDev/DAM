package com.example.hub_aplicaciones

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hub_aplicaciones.databinding.ActivityResultCalculadoraBinding
import kotlin.properties.Delegates

class ResultCalculadoraActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityResultCalculadoraBinding
    private lateinit var textoRecibido: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultCalculadoraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        capturarDatos()
        acciones()

        binding.btnVolver.setOnClickListener(this)

    }

    private fun acciones() {
        binding.resultadoRecogido.setText(textoRecibido)
    }

    private fun capturarDatos() {
        textoRecibido = intent.extras?.getString("resultado").toString()
    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            binding.btnVolver.id->{
                var intent: Intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}