package com.example.divisasapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var inputAmount: EditText
    private lateinit var outputAmount: EditText
    private lateinit var exchangeRateInput: EditText
    private lateinit var fromCurrency: Spinner
    private lateinit var toCurrency: Spinner
    private lateinit var convertButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicialización de vistas
        inputAmount = findViewById(R.id.edit1)
        outputAmount = findViewById(R.id.edit2)
        exchangeRateInput = findViewById(R.id.exchangeRateInput)
        fromCurrency = findViewById(R.id.spinner1)
        toCurrency = findViewById(R.id.spinner2)
        convertButton = findViewById(R.id.convertButton)

        // Configurar botón de conversión
        convertButton.setOnClickListener {
            convertCurrency()
        }
    }

    private fun convertCurrency() {
        val from = fromCurrency.selectedItem.toString()
        val to = toCurrency.selectedItem.toString()
        val amountStr = inputAmount.text.toString()
        val exchangeRateStr = exchangeRateInput.text.toString()

        // Validar si los campos están vacíos
        if (amountStr.isEmpty() || exchangeRateStr.isEmpty()) {
            Toast.makeText(this, "Por favor, introduce una cantidad y una tasa de cambio", Toast.LENGTH_SHORT).show()
            return
        }

        val amount = amountStr.toDouble()
        val exchangeRate = exchangeRateStr.toDouble()
        val result: Double

        // Verificar si las monedas son iguales
        result = if (from == to) {
            amount  // Si son iguales, no se realiza conversión
        } else {
            amount * exchangeRate  // Conversión basada en la tasa ingresada
        }

        // Mostrar el resultado en el campo de "Moneda de destino"
        outputAmount.setText(String.format("%.2f", result))
    }
}
