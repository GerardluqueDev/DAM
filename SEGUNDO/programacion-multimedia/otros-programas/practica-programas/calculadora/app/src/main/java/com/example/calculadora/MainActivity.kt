package com.example.calculadora

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.children
import com.example.calculadora.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var operacion = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        operacion = savedInstanceState?.getString("operacion") ?: ""
        binding.tvPantalla.text = if (operacion.isEmpty()) "0" else operacion

        //Asignación de listenners a todos los botones del loyout a la vez
        binding.btBorrar.setOnClickListener(this)
        binding.btMasMenos.setOnClickListener(this)
        binding.btPorcent.setOnClickListener(this)
        binding.btDiv.setOnClickListener(this)
        binding.btSiete.setOnClickListener(this)
        binding.btOcho.setOnClickListener(this)
        binding.btNueve.setOnClickListener(this)
        binding.btMulti.setOnClickListener(this)
        binding.btCuatro.setOnClickListener(this)
        binding.btCinco.setOnClickListener(this)
        binding.btSeis.setOnClickListener(this)
        binding.btMenos.setOnClickListener(this)
        binding.btUno.setOnClickListener(this)
        binding.btDos.setOnClickListener(this)
        binding.btTres.setOnClickListener(this)
        binding.btMas.setOnClickListener(this)
        binding.btCero.setOnClickListener(this)
        binding.btComa.setOnClickListener(this)
        binding.btIgual.setOnClickListener(this)
        binding.btPi?.setOnClickListener(this)
        binding.btAbreParentes?.setOnClickListener(this)
        binding.btCierraParentes?.setOnClickListener(this)
        binding.btAbreCorch?.setOnClickListener(this)
        binding.btCieraCorch?.setOnClickListener(this)

    }

    // Método que se ejecuta cuando se hace clic en un View (elemento de la interfaz).
    override fun onClick(v: View?) {
        when(v?.id){
            // dependiendo del botón clicado se abre un método
            binding.btBorrar.id-> limpiar()
            binding.btIgual.id-> operar()
            // Si no son el de borrar o igual, se ejecuta el metodo pasarTextoPantalla
            else -> {
                pasarTextoAPantalla(v)
            }
        }

    }

    // Método que realiza las operaciones matemáticas de una expresión introducida.
    private fun operar() {
        try {
            // Se modifica la cadena de texto `operacion` reemplazando ciertos caracteres o símbolos
            // por sus equivalentes en formato matemático válido para su evaluación:
            operacion = operacion
                .replace("x", "*")        // Cambia el símbolo "x" por "*", que es el operador de multiplicación.
                .replace("÷", "/")        // Cambia "÷" por "/", que es el operador de división.
                .replace(",", ".")        // Cambia la coma (formato de número decimal en algunos países) por un punto.
                .replace("+/-", "-")      // Cambia el símbolo de más/menos por un guion para números negativos.
                .replace("%", "/100*")    // Convierte el símbolo de porcentaje a su representación matemática.
                .replace("π", "3.1416")   // Reemplaza el símbolo π por su aproximación numérica.

            // Se utiliza la biblioteca `ExpressionBuilder` para evaluar la expresión matemática resultante.
            // Esto genera un valor numérico (resultado) que es almacenado en la variable `resultado`.
            val resultado = ExpressionBuilder(operacion).build().evaluate()

            // Se actualiza el texto de la pantalla (tvPantalla) con el resultado convertido a cadena.
            binding.tvPantalla.text = resultado.toString()
        } catch (e: Exception) {
            // Si ocurre algún error al evaluar la expresión (por ejemplo, formato inválido),
            // se muestra "Error" en la pantalla.
            binding.tvPantalla.text = "Error"
        }
    }

    fun limpiar(){
        operacion = ""
        binding.tvPantalla.text = "0"

    }

    fun pasarTextoAPantalla(v: View?){
        if (v is Button){
            operacion += v.text.toString()
            binding.tvPantalla.text = operacion


        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("operacion",operacion)
    }

}