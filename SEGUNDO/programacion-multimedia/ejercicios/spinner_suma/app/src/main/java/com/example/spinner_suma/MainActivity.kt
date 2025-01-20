package com.example.spinner_suma

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.spinner_suma.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityMainBinding
    //private lateinit var operaciones: ArrayList<String>
    private lateinit var adapter: ArrayAdapter<String>
    private var numeroUno: Int = 0
    private var numeroDos: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        numeroUno = savedInstanceState?.getInt("uno") ?: 0
        numeroDos = savedInstanceState?.getInt("dos") ?: 0

        inicializaciones()
        acciones()

    }

    private fun inicializaciones() {
        //operaciones = arrayListOf("Suma","Resta","Multiplicación","División")
        var operaciones = resources.getStringArray(R.array.spinnerOp)
        adapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1,operaciones)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter

    }

    private fun acciones() {
        binding.btCalcular.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            binding.btCalcular.id->{
                if (binding.editPrimerNumero.text.isNotEmpty() && binding.editSegundoNumero.text.isNotEmpty()){
                    var operacion: String = binding.spinner.selectedItem.toString()
                    numeroUno = binding.editPrimerNumero.text.toString().toInt()
                    numeroDos = binding.editSegundoNumero.text.toString().toInt()
                    var resultado: Double
                    when(operacion){
                        "Suma","Add"-> {
                            resultado = numeroUno + numeroDos.toDouble()
                        }
                        "Resta","Subtract"->{
                            resultado = numeroUno - numeroDos.toDouble()
                        }
                        "Multiplicación","Multiply"->{
                            resultado = numeroUno * numeroDos.toDouble()

                        }
                        "División","Divide"->{
                            resultado = numeroUno / numeroDos.toDouble()
                        }
                        else->{
                            resultado = 0.0
                        }
                    }
                    val intent: Intent = Intent(applicationContext,SegundaActivity2::class.java)
                    intent.putExtra("dato",resultado)
                    startActivity(intent)
                }else{
                    Snackbar.make(p0,"Introduce los numeros para operar",Snackbar.LENGTH_SHORT).show()
                }

            }

        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("uno",numeroUno)
        outState.putInt("dos",numeroDos)
    }
}