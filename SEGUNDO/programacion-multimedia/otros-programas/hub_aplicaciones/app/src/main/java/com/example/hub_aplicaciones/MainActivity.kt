package com.example.hub_aplicaciones

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hub_aplicaciones.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),OnClickListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btAcceso.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            binding.btAcceso.id->{
                var itemSeleccionado : String = binding.spinnerPrincipal.selectedItem.toString()
                when(itemSeleccionado){
                    "Calculadora simple"->{
                        var intent: Intent = Intent(applicationContext,CalculadoraActivity::class.java)
                        startActivity(intent)
                    }
                    "Conversor de temperaturas"->{
                        var intent: Intent = Intent(applicationContext,ConversorActivity::class.java)
                        startActivity(intent)
                    }
                    "Contador de clics"->{
                        var intent: Intent = Intent(applicationContext,ContadorActivity::class.java)
                        startActivity(intent)
                    }
                    "Generador de números aleatorios"->{
                        var intent: Intent = Intent(applicationContext,AleatoriosActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
        }
    }
}