package com.example.practica_login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practica_login.databinding.ActivityTercera2Binding
import com.google.android.material.snackbar.Snackbar

class TerceraActivity2 : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityTercera2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTercera2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        acciones()

    }

    private fun acciones() {
        binding.btRegistrarse.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            binding.btRegistrarse.id -> {
                if (binding.nombreRegistro.text.isNotEmpty()
                    && binding.apellidoRegistro.text.isNotEmpty()
                    && binding.emailRegistro.text.isNotEmpty()
                    && binding.passRegistro.text.isNotEmpty()
                ) {
                    var intent: Intent = Intent(applicationContext, MainActivity::class.java)
                    var bundle: Bundle = Bundle()
                    bundle.putString("nombre",binding.nombreRegistro.text.toString())
                    bundle.putString("email",binding.emailRegistro.text.toString())
                    intent.putExtra("datos",bundle)
                    startActivity(intent)

                } else {
                    Snackbar.make(p0, "Introduce todos los datos", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }
}