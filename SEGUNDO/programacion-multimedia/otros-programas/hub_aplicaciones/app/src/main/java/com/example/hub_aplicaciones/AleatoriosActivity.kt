package com.example.hub_aplicaciones

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hub_aplicaciones.databinding.ActivityAleatoriosBinding

class AleatoriosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAleatoriosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAleatoriosBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}