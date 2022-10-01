package com.example.App.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.core.content.edit
import com.example.App.API
import com.example.App.R


class LoginActivity : AppCompatActivity() {
    private val empresas = listOf<String>("HP", "LG", "Samsung")
    companion object {
        const val PREF_LOG = "login"
        const val KEY_LOGIN = "estaLogueado"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        /* creo el spinner */
        //val spinnerEmpresa = findViewById<Spinner>(R.id.spinnerEmpresa)
        //val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, empresas)
        //spinnerEmpresa.adapter = adaptador

       // adaptador.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        /* busco las vistas por id */
        val botonLogin = findViewById<Button>(R.id.botonLogin)
        val usuario = findViewById<EditText>(R.id.campoUsuario)
        val contrasenia = findViewById<EditText>(R.id.campoContrasenia)

        /* preferencias auto login*/
        val preferencias = getSharedPreferences(PREF_LOG, Context.MODE_PRIVATE)
        val estaLogueado = preferencias.getBoolean(KEY_LOGIN, false)

        if (estaLogueado) {
          irAHome()
        }
        botonLogin.setOnClickListener {
            val contraseniaIngresada = contrasenia.text.toString()
            val usuarioIngresado = usuario.text.toString()

            API.login(usuarioIngresado, contraseniaIngresada) { respuesta ->
                if (respuesta) {
                    irAHome()
                    preferencias.edit {
                        putBoolean(KEY_LOGIN, true)
                    }
                } else {
                    val alerta = AlertDialog.Builder(this)
                        .setTitle(getString(R.string.boton_resgistrarse))
                        .setMessage(getString(R.string.string_invalido))
                        .setPositiveButton(R.string.boton_aceptar) { _, _ ->

                        }
                    alerta.show()
                }
            }
           // if (contraseniaIngresada == "12345" && usuarioIngresado == "admin"){
                //Toast.makeText(this, "Las credenciales son validas", Toast.LENGTH_LONG).show()
            //}else {
                //Toast.makeText(this, "Las credenciales no son validas", Toast.LENGTH_LONG).show()
            //}
        }
    }

    private fun irAHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}
