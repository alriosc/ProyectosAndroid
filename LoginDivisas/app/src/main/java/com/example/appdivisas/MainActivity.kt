package com.example.appdivisas

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val rateDollarToSoles = 3.8 // Ejemplo de tasa de conversión
    private val rateSolesToDollar = 1 / rateDollarToSoles

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinnerCurrency = findViewById<Spinner>(R.id.spinner_currency)
        val editTextAmount = findViewById<EditText>(R.id.editText_amount)
        val buttonConvert = findViewById<Button>(R.id.button_convert)
        val textViewResult = findViewById<TextView>(R.id.textView_result)

        buttonConvert.setOnClickListener {
            val amount = editTextAmount.text.toString().toDoubleOrNull()
            if (amount != null) {
                val selectedCurrency = spinnerCurrency.selectedItemPosition
                val result = when (selectedCurrency) {
                    0 -> amount * rateSolesToDollar // Soles a Dólares
                    1 -> amount * rateDollarToSoles // Dólares a Soles
                    else -> 0.0
                }
                textViewResult.text = "Resultado: %.2f".format(result)
            } else {
                textViewResult.text = "Por favor, ingrese un monto válido"
            }
        }
    }
}