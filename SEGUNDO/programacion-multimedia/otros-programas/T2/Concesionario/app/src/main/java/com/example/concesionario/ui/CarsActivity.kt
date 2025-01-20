package com.example.concesionario.ui

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.concesionario.R
import com.example.concesionario.adapters.AdaptadorMarcas
import com.example.concesionario.adapters.AdaptadorModelos
import com.example.concesionario.databinding.ActivityCarsBinding
import com.example.concesionario.model.Marca
import com.example.concesionario.model.Modelo
import com.google.android.material.snackbar.Snackbar

class CarsActivity : AppCompatActivity(), OnItemSelectedListener {
    //lista
    private lateinit var listaMarcas: ArrayList<Marca>
    //adaptador
    private lateinit var adaptadorMarcas: AdaptadorMarcas
    //Lista
    private lateinit var listaModelos: ArrayList<Modelo>
    // adaptador
    private lateinit var adaptadorModelos: AdaptadorModelos

    private lateinit var binding: ActivityCarsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        instancias()

        acciones()

    }

    private fun acciones() {
        binding.spinnerMarcas.onItemSelectedListener = this
    }

    private fun instancias() {
        listaMarcas = arrayListOf(
            Marca("Mercedes", R.drawable.mercedes),
            Marca("BMW", R.drawable.bmw),
            Marca("Ford", R.drawable.ford),
            Marca("BYD", R.drawable.byd),
            Marca("Audi", R.drawable.audi),
            Marca("Peugeot", R.drawable.peugeot),
            Marca("Opel", R.drawable.opel))
        adaptadorMarcas = AdaptadorMarcas(listaMarcas,applicationContext)
        binding.spinnerMarcas.adapter = adaptadorMarcas

        listaModelos = arrayListOf(
            Modelo(
                "C 63 S E PERFORMANCE",
                "Mercedes",
                95000,
                671,
                "Deportivo de Mercedes",
                R.drawable.c63
            ),
            Modelo(
                "GT 63 S E PERFORMANCE",
                "Mercedes",
                180000,
                843,
                "Deportivo de Mercedes",
                R.drawable.gt63// Completar
            ),
            Modelo(
                "S 63 E PERFORMANCE",
                "Mercedes",
                190000,
                791,
                "Deportivo de Mercedes",
                R.drawable.e63// Completar
            ),
            Modelo(
                "E 63 S",
                "Mercedes",
                118000,
                603,
                "Deportivo de Mercedes",
                R.drawable.s63// Completar
            ),
            Modelo(
                "RS 7 Sportback",
                "Audi",
                156000,
                600,
                "Deportivo de Audi",
                R.drawable.rs7 // Completar
            ),
            Modelo(
                "RS Q8",
                "Audi",
                170000,
                600,
                "SUV deportivo de Audi",
                R.drawable.rsq8 // Completar
            ),
            Modelo(
                "S8",
                "Audi",
                160000,
                571,
                "Sedán deportivo de Audi",
                R.drawable.s8// Completar
            ),
            Modelo(
                "RS 5 Coupé",
                "Audi",
                110000,
                450,
                "Coupé deportivo de Audi",
                R.drawable.rs5 // Completar
            ),
            Modelo(
                "Mustang GT",
                "Ford",
                60000,
                450,
                "Deportivo clásico de Ford",
                R.drawable.mustangt // Completar
            ),
            Modelo(
                "Mustang Mach 1",
                "Ford",
                75000,
                480,
                "Versión de alto rendimiento del Ford Mustang",
                R.drawable.mustangmatch// Completar
            ),
            Modelo(
                "Ranger Raptor",
                "Ford",
                65000,
                288,
                "Pick-up deportiva de Ford",
                R.drawable.raptor// Completar
            ),
            Modelo(
                "Explorer ST",
                "Ford",
                70000,
                400,
                "SUV de alto rendimiento de Ford",
                R.drawable.explorerst // Completar
            ),
            Modelo(
                "508 PSE (Peugeot Sport Engineered)",
                "Peugeot",
                62000,
                360,
                "Híbrido enchufable deportivo de Peugeot",
                R.drawable.peugeot // Completar
            ),
            Modelo(
                "M3 Competition",
                "BMW",
                113000,
                510,
                "Deportivo sedán de BMW",
                R.drawable.m3 // Completar
            ),
            Modelo(
                "X5 M",
                "BMW",
                160000,
                625,
                "SUV de alto rendimiento de BMW",
                R.drawable.x5 // Completar
            ),
            Modelo(
                "M4 Convertible",
                "BMW",
                120000,
                510,
                "Convertible deportivo de BMW",
                R.drawable.m4 // Completar
            ),
            Modelo(
                "i4 M50",
                "BMW",
                75000,
                544,
                "Deportivo eléctrico de BMW",
                R.drawable.i4 // Completar
            ),
            Modelo(
                "M8 Competition Coupé",
                "BMW",
                180000,
                625,
                "Coupé de alto rendimiento de BMW",
                R.drawable.m8 // Completar
            ),
            Modelo(
                "Han EV",
                "BYD",
                50000,
                490,
                "Sedán eléctrico de BYD",
                R.drawable.han // Completar
            ),
            Modelo(
                "Tang EV",
                "BYD",
                70000,
                517,
                "SUV eléctrico de BYD",
                R.drawable.tan // Completar
            ),
            Modelo(
                "Seal",
                "BYD",
                55000,
                530,
                "Deportivo eléctrico de BYD",
                R.drawable.seal // Completar
            )

        )
        adaptadorModelos = AdaptadorModelos(listaModelos, this)
        binding.recyclerModelos.adapter = adaptadorModelos
        if (resources.configuration.orientation == 1){
            binding.recyclerModelos.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        }else if (resources.configuration.orientation == 2){
            binding.recyclerModelos.layoutManager =
                GridLayoutManager(this, 2)
        }

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        var marcaSeleccionada: Marca = adaptadorMarcas.getItem(p2)
        /*Snackbar.make(
            binding.root,
            "La marca seleccionada es ${marcaSeleccionada.nombre}",
            Snackbar.LENGTH_SHORT
        ).show()*/
        // Vas a la lista y te quedas solo con los modelos que tinen como atributo marca la misma que el spinner tiene seleccionada
        val listaFiltrada: ArrayList<Modelo> = listaModelos.filter {
            it.marca.equals(marcaSeleccionada.nombre, true)
        } as ArrayList<Modelo>
        //actualizar la lista
        adaptadorModelos.actualizarLista(listaFiltrada)
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}