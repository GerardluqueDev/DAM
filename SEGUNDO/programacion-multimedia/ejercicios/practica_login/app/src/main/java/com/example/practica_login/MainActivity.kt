package com.example.practica_login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practica_login.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        acciones()
    }

    private fun acciones() {
        binding.btAcceder.setOnClickListener(this)
        binding.btRegistro.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            binding.btAcceder.id -> {
                if (binding.editNombre.text.isNotEmpty() && binding.editEmail.text.isNotEmpty()) {
                    var intent: Intent = Intent(applicationContext, SegundaActivity2::class.java)
                    var bundle: Bundle = Bundle()
                    bundle.putString("nombre", binding.editNombre.text.toString())
                    bundle.putString("email", binding.editEmail.text.toString())
                    intent.putExtra("datos", bundle)
                    startActivity(intent)
                } else {
                    Snackbar.make(p0, "Introduce todos los datos", Snackbar.LENGTH_SHORT).show()
                }
            }

            binding.btRegistro.id -> {
                var intent: Intent = Intent(applicationContext, TerceraActivity2::class.java)
                startActivity(intent)
            }

        }
    }
}