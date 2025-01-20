package com.example.meteorologia.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import com.example.meteorologia.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        inicializacion()
        aciones()
    }

    private fun inicializacion() {
        binding.btInicioSesion.setOnClickListener(this)
        binding.btLinck.setOnClickListener(this)
    }

    private fun aciones() {

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            binding.btInicioSesion.id -> {
                if (binding.editEmail.text.isNotEmpty() && binding.editPass.text.isNotEmpty()){
                    val intent = Intent(applicationContext, Informacion_Activity::class.java)
                    startActivity(intent)
                }else{
                    Snackbar.make(binding.root, "Debes ingresar todos los campos", Snackbar.LENGTH_SHORT).show()
                }


            }
            binding.btLinck.id -> {
                var intent = Intent(applicationContext, Registro_Activity::class.java)
                startActivity(intent)
            }

        }
    }
}