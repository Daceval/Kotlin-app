package com.example.App.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageView
import androidx.core.content.edit
import com.example.App.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        findViewById<ImageView>(R.id.imageWeather).setOnClickListener {
            val intent = Intent(this, ClimaActivity::class.java)
            startActivity(intent)

        }

        findViewById<ImageView>(R.id.imageNotes).setOnClickListener {
            val intent = Intent(this, NotasActivity::class.java)
            startActivity(intent)

        }

       findViewById<ImageView>(R.id.imageNews).setOnClickListener {
           val intent = Intent(this, InfoActivity::class.java)
           startActivity(intent)
       }

        findViewById<ImageView>(R.id.imageMap).setOnClickListener {
            intent = Intent(this, UbicameActivity::class.java)
            startActivity(intent);
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflador = MenuInflater(this)
        inflador.inflate(R.menu.home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.item_salir) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()

            val preferencias = getSharedPreferences(LoginActivity.PREF_LOG, Context.MODE_PRIVATE)
            preferencias.edit {
                putBoolean(LoginActivity.KEY_LOGIN, false)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}