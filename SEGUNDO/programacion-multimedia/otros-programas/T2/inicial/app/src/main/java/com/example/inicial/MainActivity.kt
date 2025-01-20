package com.example.inicial

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.inicial.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

//Lógica de una pantalla -> Activity
class MainActivity : AppCompatActivity() {
    // primer método del ciclo de vida (OBLIGATORIO)
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //inicializamos el binding (Siempre es igual así que hay que aprenderlo)
        binding = ActivityMainBinding.inflate(layoutInflater)

        //asocia parte gráfica con parte lógica
        setContentView(binding.root) //Sólo de tras de esta linea, lo gáfico y lo lógico estan juntos

        binding.btPulsar.setOnClickListener { //identificamos el boton y le decimos que es la accion de clicar
            //aquí ponemos lo que queremos que haga el boton al pulsarlo
            Snackbar.make(binding.root,"Enhorabuena, completada la primera app", Snackbar.LENGTH_SHORT).show()
        }

    }
}