package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referencias a los elementos de la interfaz
        val editTextNombre: EditText = findViewById(R.id.editTextText)
        val spinnerComida: Spinner = findViewById(R.id.spinner_items)
        val radioGroupFrecuencia: RadioGroup = findViewById(R.id.radioGroup)
        val btnEnviar: Button = findViewById(R.id.btnEnviar)

        // Carga los datos desde el string-array del archivo strings.xml
        val items = resources.getStringArray(R.array.spinner_items1)

        // Configura el adaptador para el Spinner con un diseño personalizado
        val adapter = ArrayAdapter(this, R.layout.spinner_item, items)
        adapter.setDropDownViewResource(R.layout.spinner_item) // También usa el diseño personalizado para el dropdown
        spinnerComida.adapter = adapter

        // Configura el OnClickListener para el botón de envío
        btnEnviar.setOnClickListener {

            // Validar que el campo nombre no esté vacío
            if (editTextNombre.text.isEmpty()) {
                Toast.makeText(this, "Por favor, ingresa tu nombre", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Obtener el nombre ingresado
            val nombre = editTextNombre.text.toString()

            // Obtener la comida favorita seleccionada
            val comidaFavorita = spinnerComida.selectedItem.toString()

            // Obtener la frecuencia de consumo seleccionada
            val selectedRadioButtonId = radioGroupFrecuencia.checkedRadioButtonId
            val frecuenciaConsumo: String = if (selectedRadioButtonId != -1) {
                val radioButton: RadioButton = findViewById(selectedRadioButtonId)
                radioButton.text.toString()
            } else {
                "No seleccionaste frecuencia"
            }

            // Mostrar el resumen en un Toast
            val resumen = "Nombre: $nombre\nComida favorita: $comidaFavorita\nFrecuencia: $frecuenciaConsumo"
            Toast.makeText(this, resumen, Toast.LENGTH_LONG).show()
        }
    }
}
