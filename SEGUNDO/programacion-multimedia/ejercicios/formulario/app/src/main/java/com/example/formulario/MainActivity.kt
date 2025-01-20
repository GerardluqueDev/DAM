package com.example.formulario

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.formulario.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var nombre: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        nombre = savedInstanceState?.getString("nombre") ?: ""
        binding.editNombre.setText(nombre)
        acciones()

    }

    private fun acciones() {
        binding.btAcceder.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            binding.btAcceder.id->{
                val intent: Intent = Intent(this,Segunda_Activity::class.java)
                intent.putExtra("nombre",binding.editNombre.text.toString())
                intent.putExtra("apellido",binding.editApellido.text.toString())
                intent.putExtra("email",binding.editEmail.text.toString())
                startActivity(intent)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        nombre = binding.editNombre.text.toString()
        outState.putString("nombre",nombre)
    }
}