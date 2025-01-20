package com.example.concesionario.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.RadioGroup
import android.widget.RadioGroup.OnCheckedChangeListener
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.concesionario.R
import com.example.concesionario.databinding.ActivityLoginBinding
import com.example.concesionario.model.Usuario
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity(), OnClickListener, OnCheckedChangeListener {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var usuarios: ArrayList<Usuario>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        instancias()
        initGUI()
        acciones()

    }

    private fun instancias() { // Inicializo una lista ficticia de usuarios contra la que voy a hacer el login
        usuarios = arrayListOf(
            Usuario("Borja","borja@hotmail.com","1234borja"),
            Usuario("Gerard","gerard@gmail.com","1234gerard"),
            Usuario("Pepe","pepe@yahoo.com","1234pepe"))
    }

    private fun initGUI() { //Pone marcado por defecto el radiobutton registrado
        binding.grupoUsuario.check(binding.radioRegistrado.id)
    }

    private fun acciones() { // Pongo a escuchar tanto al botón como al grupo de radiobutton

        binding.btAcceso.setOnClickListener(this)
        binding.grupoUsuario.setOnCheckedChangeListener(this)

    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            binding.btAcceso.id->{
                //Cambiar de escena

                val radioSeleccionado:Int = binding.grupoUsuario.checkedRadioButtonId
                // Creo el intent para pasar de una pantalla a otra
                val intent: Intent = Intent(applicationContext, CarsActivity::class.java)
                when(radioSeleccionado){
                    binding.radioInvitado.id->{
                        //Si soy invitado accedo directamente
                        startActivity(intent)
                    }
                    binding.radioRegistrado.id->{
                        //Si soy registrado miro los edit y busco la lista
                        val usuario: Usuario? = usuarios.find {
                            it.correo.equals(binding.editCorreo.text.toString())
                                    && it.pass.equals(binding.editPass.text.toString())
                        }
                        if (usuario == null){
                            Snackbar.make(binding.root, "Error al iniciar", Snackbar.LENGTH_SHORT).show()
                        }else{
                            startActivity(intent)
                        }
                    }
                }
            }
        }
    }

    override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
        when(p0?.id){
            binding.grupoUsuario.id->{
                when(p1){
                    binding.radioInvitado.id->{
                        binding.editCorreo.isEnabled = false
                        binding.editPass.isEnabled = false
                    }
                    binding.radioRegistrado.id->{
                        binding.editCorreo.isEnabled = true
                        binding.editPass.isEnabled = true
                    }
                }
            }
        }
    }
}