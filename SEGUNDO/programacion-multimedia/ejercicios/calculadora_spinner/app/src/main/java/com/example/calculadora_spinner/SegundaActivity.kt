package com.example.calculadora_spinner

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculadora_spinner.databinding.ActivitySegundaBinding

class SegundaActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySegundaBinding
    private lateinit var numeroUno: String
    private lateinit var numeroDos: String
    private lateinit var resultado: String
    private lateinit var operacion: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySegundaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var bundle: Bundle? = intent.getBundleExtra("datos")
        numeroUno = bundle?.getInt("uno").toString()
        numeroDos = bundle?.getInt("dos").toString()
        resultado = bundle?.getDouble("resultado").toString()
        operacion = bundle?.getString("operacion").toString()
        binding.textoRecoger.text = "El resultado de $numeroUno $operacion " +
                "$numeroDos es igual a $resultado"

    }
}