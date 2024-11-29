package ramosdelgado.angel.tarea02angelramosdelgado

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PersonajesAdapter(private val personajes: List<Personaje>) : RecyclerView.Adapter<PersonajesAdapter.PersonajeViewHolder>() {

    // ViewHolder que se utilizará para cada ítem en el RecyclerView
    class PersonajeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val personajeImagen: ImageView = view.findViewById(R.id.personaje_imagen)
        val personajeNombre: TextView = view.findViewById(R.id.personaje_nombre)
        val personajeDescripcion: TextView = view.findViewById(R.id.personaje_descripcion)
    }

    // Método que infla la vista para cada ítem
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonajeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_personaje, parent, false)
        return PersonajeViewHolder(view)
    }

    // Método que vincula los datos con cada ítem del RecyclerView
    override fun onBindViewHolder(holder: PersonajeViewHolder, position: Int) {
        val personaje = personajes[position]
        holder.personajeImagen.setImageResource(personaje.imageResId)
        holder.personajeNombre.text = personaje.name
        holder.personajeDescripcion.text = personaje.description

        // Definir la acción de clic
        holder.itemView.setOnClickListener {
            // Crear un Intent para abrir la pantalla de detalles
            val intent = Intent(holder.itemView.context, ActividadDetallePersonaje::class.java).apply {
                putExtra("nombrePersonaje", personaje.name)
                putExtra("descripcionPersonaje", personaje.description)
                putExtra("imagenPersonaje", personaje.imageResId)
            }
            holder.itemView.context.startActivity(intent)
        }
    }


    // Método que devuelve el número de ítems en la lista
    override fun getItemCount(): Int = personajes.size
}
