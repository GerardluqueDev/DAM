package com.example.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.login.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var nombre: String = " "
    private var apellido: String = " "
    private var email: String = " "
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        nombre = binding.editNombre.text.toString()
        apellido = binding.editApellido.text.toString()
        email = binding.editEmail.text.toString()
        //Recuperar datos en caso de cambuio de estado
        nombre = savedInstanceState?.getString("nombre") ?: ""
        apellido = savedInstanceState?.getString("apellido") ?: ""
        email = savedInstanceState?.getString("nombre") ?: ""

        acciones()


    }

    private fun acciones() {
        binding.btAcceder.setOnClickListener(this)
        binding.btRegistrarse.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            binding.btAcceder.id->{
                var intent: Intent = Intent(this, segundaActivity::class.java)
                var bundle: Bundle = Bundle()
                bundle.putString("nombre", binding.editNombre.text.toString())
                bundle.putString("apellido", binding.editApellido.text.toString())
                bundle.putString("email", binding.editEmail.text.toString())
                intent.putExtra("datos",bundle)
                startActivity(intent)
            }
            binding.btRegistrarse.id->{}
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("nombre",nombre)
        outState.putString("apellido",apellido)
        outState.putString("email",email)

    }
}