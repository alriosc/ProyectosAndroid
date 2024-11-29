package com.example.calculadoraapp

import android.icu.text.DecimalFormat
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    val SUMA = "+"
    val RESTA = "-"
    val MULTIPLICACION = "*"
    val DIVISION = "/"
    val PORCENTAJE = "%"

    var operacionActual = " "
    var primerNum:Double = Double.NaN
    var segunNum:Double = Double.NaN

    lateinit var tvTemp:TextView
    lateinit var tvResult:TextView

    lateinit var formatoDecimal:DecimalFormat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        formatoDecimal = DecimalFormat("#.#######")
        tvTemp = findViewById(R.id.tvTemp)
        tvResult = findViewById(R.id.tvResult)
    }
    fun cambiarOperador(b: View) {
        if (tvTemp.text.isNotEmpty() || primerNum.toString() != "NaN") {

            calcular()
            val boton: Button = b as Button
            operacionActual = when (boton.text.toString().trim()) {
                "÷" -> "/"
                "×" -> "*"
                else -> boton.text.toString().trim()
            }
            val simboloMostrar = when (operacionActual) {
                "/" -> "÷"
                "*" -> "×"
                else -> operacionActual
            }

            if (tvTemp.text.toString().isEmpty()) {
                tvTemp.text = tvResult.text
            }

            tvResult.text = formatoDecimal.format(primerNum) + simboloMostrar
            tvTemp.text = ""
        }
    }
        fun calcular(){
            try {
            if(primerNum.toString()!="NaN"){
                if(tvTemp.text.toString().isEmpty()){
                    tvTemp.text=tvResult.text.toString()
                }
                segunNum = tvTemp.text.toString().toDouble()
                tvTemp.text=" "

                when(operacionActual){
                    "+"-> primerNum = (primerNum+segunNum)
                    "-"-> primerNum = (primerNum-segunNum)
                    "*"-> primerNum = (primerNum*segunNum)
                    "/"-> primerNum = (primerNum/segunNum)
                    "%"-> primerNum =(primerNum * segunNum / 100)
                }
            }else{
                primerNum = tvTemp.text.toString().toDouble()
            }
        }catch(e:Exception){
            }
        }
        fun seleccionarNumero(b:View){
            val boton:Button = b as Button
            tvTemp.text = tvTemp.text.toString() + boton.text.toString()
        }
        fun igual(b:View){
            calcular()
            tvResult.text=formatoDecimal.format(primerNum)
            operacionActual=" "
        }
        fun borrar(b: View) {
            val boton: Button = b as Button
            if (boton.text.toString().trim() == "C") {
                if(tvTemp.text.toString().isNotEmpty()){
                    var datosActuales:CharSequence=tvTemp.text as CharSequence
                    tvTemp.text=datosActuales.subSequence(0,datosActuales.length-1)
                }else{
                    primerNum=Double.NaN
                    segunNum=Double.NaN
                    tvTemp.text=" "
                    tvResult.text=" "
                }
            } else if (boton.text.toString().trim() == "CA") {
                primerNum=Double.NaN
                segunNum=Double.NaN
                tvTemp.text=" "
                tvResult.text=" "
            }
        }
    }
