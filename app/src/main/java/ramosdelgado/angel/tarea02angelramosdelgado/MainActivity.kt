package ramosdelgado.angel.tarea02angelramosdelgado

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Switch
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configurar la Toolbar como ActionBar
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Inicializar el RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        // Crear una lista de personajes usando las cadenas desde los recursos
        val personajes = listOf(
            Personaje(getString(R.string.mario_name), getString(R.string.mario_description), R.drawable.mario),
            Personaje(getString(R.string.luigi_name), getString(R.string.luigi_description), R.drawable.luigi),
            Personaje(getString(R.string.peach_name), getString(R.string.peach_description), R.drawable.peach),
            Personaje(getString(R.string.toad_name), getString(R.string.toad_description), R.drawable.toad)
        )

        // Configurar el LayoutManager para el RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Asignar el Adapter al RecyclerView
        recyclerView.adapter = PersonajesAdapter(personajes)

        // Configuración para los botones de cambio de idioma
        val botonCambiarIdioma: Button = findViewById(R.id.botonCambiarIdioma)
        botonCambiarIdioma.setOnClickListener {
            cambiarIdioma("en")  // Cambiar a inglés
        }

        val botonCambiarIdiomaEspañol: Button = findViewById(R.id.botonCambiarIdiomaEspañol)
        botonCambiarIdiomaEspañol.setOnClickListener {
            cambiarIdioma("es")  // Cambiar a español
        }

        // Configuración del DrawerLayout
        drawerLayout = findViewById(R.id.drawerLayout)

        // Configuración del ActionBarDrawerToggle
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Escuchar los cambios del Switch para cambiar el idioma
        val switchIdioma: Switch = findViewById(R.id.switch_idioma)
        switchIdioma.setOnCheckedChangeListener { _, isChecked ->
            val idioma = if (isChecked) "en" else "es"
            cambiarIdioma(idioma)
        }

        // Mostrar el botón de menú
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // Función para cambiar el idioma
    private fun cambiarIdioma(idioma: String) {
        val locale = Locale(idioma)
        Locale.setDefault(locale)

        val config = Configuration()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(locale)
        } else {
            config.locale = locale
        }

        resources.updateConfiguration(config, resources.displayMetrics)

        // Reinicia la actividad para que se apliquen los cambios
        val intent = intent
        finish()
        startActivity(intent)
    }

    // Inflar el menú en la AppBar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    // Manejar las opciones del menú
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_acerca_de -> {
                mostrarDialogAcercaDe()
                true
            }
            android.R.id.home -> {  // Este es para el icono del Drawer
                drawerLayout.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Mostrar el diálogo "Acerca de..."
    private fun mostrarDialogAcercaDe() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Acerca de")
        builder.setMessage("Aplicación desarrollada por Ángel Ramos. Versión 1.0.")
        builder.setIcon(R.mipmap.ic_launcher) // Icono de la app
        builder.setPositiveButton("Cerrar") { dialog, _ -> dialog.dismiss() }
        builder.create().show()
    }

    // Manejo de eventos de clic en el menú lateral
    private fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                // Acción para "Home"
            }
            R.id.nav_ajustes -> {
                // Acción para "Ajustes"
            }
            R.id.nav_idioma -> {
                // Acción para "Idioma"
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    // Manejar el evento de retroceso para el Drawer
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
