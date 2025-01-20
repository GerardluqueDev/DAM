package com.example.contador

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MostrarContador : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mostrar_contador)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        //obtener la información de la propia Intent. Con la primera línea recuperamos la Intent que abrió la actividad (mediante intent) para
        //consultar el valor de la clave «contador» contenida en esta. Esto lo hacemos con el método
        //getIntExtra, para el que especificamos la clave a recuperar y el valor por defecto si esta no
        //se encuentra
        val contador:Int? = intent.getIntExtra("contador", 0)

        //modificar el TextView (muestraContador) de esta
        //segunda ventana con el valor del contador extraído de la Intent
        val contadorTextView = findViewById<TextView>(R.id.muestraContador)
        contadorTextView.setText(contador.toString())

        //Finalmente, cuando el usuario pulse en el botón Close, cerraremos esta actividad utilizando
        //el método finish() que esta nos proporciona
        val btClose = findViewById<Button>(R.id.btClose)
        btClose.setOnClickListener{
            finish()
        }

    }
}