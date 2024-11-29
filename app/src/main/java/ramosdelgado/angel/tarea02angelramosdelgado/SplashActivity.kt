package ramosdelgado.angel.tarea02angelramosdelgado

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Redirigir a MainActivity después de 2-3 segundos
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()  // Para evitar que el usuario regrese a la pantalla de inicio
        }, 3000)  // 3 segundos de retraso
    }
}
