package com.example.calculadora_spinner

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculadora_spinner.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        acciones()

    }

    private fun acciones() {
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when (p0?.id) {
                    binding.spinner.id->{
                        if (binding.numeroUno.text.isNotEmpty() && binding.numeroDos.text.isNotEmpty()) {
                            var opercion = binding.spinner.selectedItem.toString()
                            var numeroUno = binding.numeroUno.text.toString().toInt()
                            var numeroDos = binding.numeroDos.text.toString().toInt()
                            var resultado: Double
                            when(opercion){
                                "+"->{
                                    resultado = numeroUno + numeroDos.toDouble()
                                }
                                "-"->{
                                    resultado = numeroUno - numeroDos.toDouble()
                                }
                                "x"->{
                                    resultado = numeroUno * numeroDos.toDouble()
                                }
                                "/"->{
                                    resultado = numeroUno / numeroDos.toDouble()
                                }
                                else->{
                                    resultado = 0.0
                                }
                            }
                            var intent: Intent = Intent(applicationContext,SegundaActivity::class.java)
                            var bundle: Bundle = Bundle()
                            bundle.putInt("uno",numeroUno)
                            bundle.putInt("dos",numeroDos)
                            bundle.putDouble("resultado",resultado)
                            bundle.putString("operacion",opercion)
                            intent.putExtra("datos",bundle)
                            startActivity(intent)
                        }else{
                            Snackbar.make(p0,"Debe de llenar los campos",Snackbar.LENGTH_SHORT).show()
                        }
                    }

                }

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }
}