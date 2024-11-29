package ramosdelgado.angel.tarea02angelramosdelgado

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ActividadDetallePersonaje : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_detalle_personaje)

        // Obtener los datos enviados desde la actividad principal
        val nombrePersonaje = intent.getStringExtra("nombrePersonaje")
        val descripcionPersonaje = intent.getStringExtra("descripcionPersonaje")
        val imagenPersonaje = intent.getIntExtra("imagenPersonaje", 0)

        // Inicializar las vistas
        val imagenPersonajeView: ImageView = findViewById(R.id.imagenPersonajeDetalle)
        val nombrePersonajeTextView: TextView = findViewById(R.id.nombrePersonajeDetalle)
        val descripcionPersonajeTextView: TextView = findViewById(R.id.descripcionPersonajeDetalle)
        val detallesTextView: TextView = findViewById(R.id.textViewDetalles)

        // Configurar los datos de imagen, nombre y descripción
        imagenPersonajeView.setImageResource(imagenPersonaje)
        nombrePersonajeTextView.text = nombrePersonaje
        descripcionPersonajeTextView.text = descripcionPersonaje

        // Mostrar los detalles del personaje en el TextView centrado
        val detallesDelPersonaje = when (nombrePersonaje) {
            "Luigi" -> "Luigi, el hermano de Mario, es conocido por su valentía y su habilidad para superar desafíos."
            "Peach" -> "Peach, la princesa del Reino Champiñón, es valiente y siempre ayuda a Mario y sus amigos."
            "Mario" -> "Mario es el héroe más famoso del Reino Champiñón, siempre dispuesto a salvar a la princesa Peach."
            "Toad" -> "Toad es un fiel sirviente del Reino Champiñón y un amigo de Mario, siempre listo para ayudar."
            else -> "Detalles del personaje no disponibles."
        }

        detallesTextView.text = detallesDelPersonaje

        // Configurar el botón para editar el personaje
        val editarButton: Button = findViewById(R.id.editarButton)
        editarButton.setOnClickListener {
            val intent = Intent(this, ActividadEditarPersonaje::class.java).apply {
                putExtra("nombrePersonaje", nombrePersonaje)
                putExtra("descripcionPersonaje", descripcionPersonaje)
            }
            startActivityForResult(intent, 1)  // 1 es el código de solicitud para identificar la respuesta
        }
    }

    // Recibir los resultados de la actividad de edición
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Comprobar si se está recibiendo una respuesta de la actividad de edición
        if (requestCode == 1 && resultCode == RESULT_OK) {
            val nuevoNombre = data?.getStringExtra("nombrePersonaje")
            val nuevaDescripcion = data?.getStringExtra("descripcionPersonaje")

            // Actualizar la UI con los nuevos valores
            val nombrePersonajeTextView: TextView = findViewById(R.id.nombrePersonajeDetalle)
            val descripcionPersonajeTextView: TextView = findViewById(R.id.descripcionPersonajeDetalle)

            if (nuevoNombre != null && nuevaDescripcion != null) {
                nombrePersonajeTextView.text = nuevoNombre
                descripcionPersonajeTextView.text = nuevaDescripcion
                Toast.makeText(this, "Cambios guardados", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "No se pudieron guardar los cambios", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
