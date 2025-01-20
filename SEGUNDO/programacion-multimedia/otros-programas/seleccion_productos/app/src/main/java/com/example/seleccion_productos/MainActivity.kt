package com.example.seleccion_productos

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.RadioButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.seleccion_productos.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

//

class MainActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btCartaAnillo.setOnClickListener(this)
        binding.btCartaSauron.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            binding.btCartaAnillo.id->{
                if(binding.checkAnillo.isChecked && binding.radioGrup.checkedRadioButtonId != -1){
                    val nombre = binding.textAnillo.text.toString()
                    val seleccionRadioButonId = binding.radioGrup.checkedRadioButtonId
                    val radioSeleccionado = findViewById<RadioButton>(seleccionRadioButonId)
                    val textoSeleccionado = radioSeleccionado.text.toString()
                    val precio = verPrecio(textoSeleccionado)
                    val intent: Intent = Intent(application,SegundaAvtivity::class.java)
                    val bundle: Bundle = Bundle()
                    bundle.putString("nombreAnillo",nombre)
                    bundle.putDouble("precioCarta",precio)
                    intent.putExtra("datos",bundle)
                    startActivity(intent)
                }else{
                    Snackbar.make(binding.root,"Selecciona una carta",Snackbar.LENGTH_SHORT).show()
                }
            }
            binding.btCartaSauron.id->{
                if(binding.checkSauron.isChecked && binding.radioGrup.checkedRadioButtonId != -1){
                    val nombre = binding.textSauron.text.toString()
                    val seleccionRadioButonId = binding.radioGrup.checkedRadioButtonId
                    val radioSeleccionado = findViewById<RadioButton>(seleccionRadioButonId)
                    val textoSeleccionado = radioSeleccionado.text.toString()
                    val precio = verPrecio(textoSeleccionado)
                    val intent: Intent = Intent(application,SegundaAvtivity::class.java)
                    val bundle: Bundle = Bundle()
                    bundle.putString("nombreAnillo",nombre)
                    bundle.putDouble("precioCarta",precio)
                    intent.putExtra("datos",bundle)
                    startActivity(intent)
                }else{
                    Snackbar.make(binding.root,"Selecciona una carta",Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun verPrecio(seleccion: String): Double {
        return when(seleccion){
            "Común"->{
                return 0.2
            }
            "Infrecuente"->{
                return 1.0
            }
            "Rara"->{
                return 10.0
            }
            "Mítica"->{
                return 125.0
            }
            else->{
                return 0.0
            }

        }
    }
}
