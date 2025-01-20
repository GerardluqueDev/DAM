package com.example.hub_aplicaciones

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hub_aplicaciones.databinding.ActivityContadorBinding

class ContadorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContadorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContadorBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_contador)
    }
}