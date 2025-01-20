package com.example.estados

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.CompoundButton
import android.widget.CompoundButton.OnCheckedChangeListener
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.estados.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener, OnCheckedChangeListener {
    private var contador = 0
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //Quiero guardar en contador, la instancia que hemos guardado, en el caso que sea null
        // quiero que coja lo que está tras el operador elvis ?: es decir, el 0
        contador = savedInstanceState?.getInt("contador") ?: 0
        binding.textoNumero.text = contador.toString() //Tras recuperarlo, lo mostramos

        binding.btnAnadir.setOnClickListener(this) //Escuchador del botón
        binding.btnReset?.setOnClickListener(this)
        binding.checkEdicion.setOnCheckedChangeListener(this) //Escuchador del checkbox para cuando cambie de true a false o al revés


    }

    override fun onClick(v: View?) {
        when(v?.id){  //Cuando el id del elemento que ha provacado la pulsacion...
            binding.btnAnadir.id->{ // su identificación sea este, haré esto que tengo en los corchetes...
                if (binding.editEdicion.text.isNotEmpty()){ //Sie el editText no esta vacío
                     contador++
                    binding.textoNumero.text = contador.toString()
                    binding.editEdicion.text.clear()
                }
            }
            binding.btnReset?.id->{
                contador = 0
                binding.textoNumero.text = contador.toString()
            }
        }
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        when(buttonView?.id){
            binding.checkEdicion.id->{
                binding.editEdicion.isEnabled = binding.checkEdicion.isChecked
            }
        }
    }

    //Método para no perder las cosas cuando hay giro de pantalla o otro cambio de configuración
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //Se ejecuta para guardar el estado justo antes de romper la app
        outState.putInt("contador", contador) //Lo que quiero guardar es esto (llave, dato)

    }
}