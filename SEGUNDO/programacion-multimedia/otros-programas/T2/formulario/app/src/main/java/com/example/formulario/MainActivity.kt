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
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //onClickListener
        binding.botonLogin.setOnClickListener(this)
        binding.botonLimpiar.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        //Que boton se ha pulsado?
            //botonLogin -> Login
            //botonLimpiar -> Limpia
        when(v?.id){
            binding.botonLogin.id -> {
                if (binding.editCorreo.text.isNotEmpty() && binding.editPass.text.isNotEmpty()){
                    //origen - destino
                    val intent: Intent = Intent(applicationContext, SecondActivity::class.java)
                    val bundle: Bundle = Bundle()
                    bundle.putString("correo", binding.editCorreo.text.toString())
                    intent.putExtra("datos", bundle)
                    startActivity(intent)
                } else {
                    Snackbar.make(binding.root, "Faltan datos", Snackbar.LENGTH_SHORT).show()
                }
            }
            binding.botonLimpiar.id -> {
                limpiar()
            }
        }
    }

    fun limpiar(){
        binding.editPass.text.clear()
        binding.editCorreo.text.clear()
    }

    override fun onRestart() {
        super.onRestart()
        limpiar()
    }
}