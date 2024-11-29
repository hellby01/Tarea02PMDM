package ramosdelgado.angel.tarea02angelramosdelgado

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ActividadEditarPersonaje : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_personaje)

        // Obtener los datos enviados desde la actividad de detalles
        val nombreOriginal = intent.getStringExtra("nombrePersonaje")
        val descripcionOriginal = intent.getStringExtra("descripcionPersonaje")

        // Inicializar las vistas
        val nombreEditText: EditText = findViewById(R.id.editarNombrePersonaje)
        val descripcionEditText: EditText = findViewById(R.id.editarDescripcionPersonaje)
        val textViewDetalles: TextView = findViewById(R.id.textViewDetalles)
        val guardarButton: Button = findViewById(R.id.guardarCambiosButton)

        // Establecer los valores originales en los EditText
        nombreEditText.setText(nombreOriginal)
        descripcionEditText.setText(descripcionOriginal)

        // Configurar los detalles según el personaje
        when (nombreOriginal) {
            "Luigi" -> textViewDetalles.text = "Detalles importantes de Luigi."
            "Mario" -> textViewDetalles.text = "Detalles importantes de Mario."
            "Toad" -> textViewDetalles.text = "Detalles importantes de Toad."
            else -> textViewDetalles.text = "Detalles del personaje no disponibles."
        }

        // Configurar el botón de guardar
        guardarButton.setOnClickListener {
            val nuevoNombre = nombreEditText.text.toString()
            val nuevaDescripcion = descripcionEditText.text.toString()

            // Validar que los campos no estén vacíos
            if (nuevoNombre.isNotEmpty() && nuevaDescripcion.isNotEmpty()) {
                // Aquí podrías guardar los cambios en una base de datos o SharedPreferences
                // Por ejemplo, puedes usar un Toast para mostrar que se guardaron los cambios
                Toast.makeText(this, "Cambios guardados", Toast.LENGTH_SHORT).show()

                // Puedes devolver los datos modificados a la actividad anterior si es necesario
                val resultadoIntent = Intent()
                resultadoIntent.putExtra("nombrePersonaje", nuevoNombre)
                resultadoIntent.putExtra("descripcionPersonaje", nuevaDescripcion)
                setResult(RESULT_OK, resultadoIntent)
                finish()
            } else {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
