package com.example.login

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.login.databinding.ActivityMainBinding
import com.example.login.databinding.ActivitySegundaBinding

class segundaActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySegundaBinding
    private var bundleRecuperado: Bundle? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySegundaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bundleRecuperado = intent.extras?.getBundle("datos")
        binding.textNombre.text = bundleRecuperado?.getString("nombre") ?: "Sin nombre"

    }
}