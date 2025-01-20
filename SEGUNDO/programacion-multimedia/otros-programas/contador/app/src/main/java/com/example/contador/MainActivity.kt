package com.example.contador

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    var contador = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        //referencia al TextView
        val textViewContador = findViewById<TextView>(R.id.textViewContador)

        //Inicializamos el TextView con el contador a 0
        textViewContador.setText(contador.toString())

        //referencia al boton
        val btAdd = findViewById<Button>(R.id.btAdd)

        //Asociamos una expresión lamda como respuesta (callback) al evento "clic" sobre el botón
        btAdd.setOnClickListener{
            contador++
            textViewContador.setText(contador.toString())

        //capturamos el evento clic sobre el botón btOpen
            val btNewActivity = findViewById<Button>(R.id.btOpen)

            //completamos este callback con el código necesario para crear y lanzar la Intent
            btNewActivity.setOnClickListener {
                val intent = Intent(baseContext, MostrarContador::class.java)
                intent.putExtra("contador", contador)
                startActivity(intent)
            }
        }
    }

    override fun onSaveInstanceState(estado: Bundle) {
        super.onSaveInstanceState(estado)
        estado.putInt("CLAVE", contador)
    }

    override fun onRestoreInstanceState(estado: Bundle) {
        super.onRestoreInstanceState(estado)
        contador = estado.getInt("CLAVE")
        val textViewContador = findViewById<TextView>(R.id.textViewContador)
        textViewContador.setText(contador.toString())    }

}