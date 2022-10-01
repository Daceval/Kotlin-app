package com.example.App.activities

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import com.example.App.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class UbicameActivity : AppCompatActivity() {
    companion object {
        const val REQUEST_CODE = 123
    }

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ubicame)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        findViewById<Button>(R.id.botonUbicar).setOnClickListener {
            pedirUbicacion()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun pedirUbicacion() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            //pedimos permiso, porque no lo tengo
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ), REQUEST_CODE
            )
        } else {
            //tengo permiso
            val location = fusedLocationClient.lastLocation.addOnSuccessListener {
                if (it != null) {
                    val textoUbicacion = findViewById<TextView>(R.id.textoUbicacion)
                    textoUbicacion.text = "Latitud : ${it.latitude} ${it.longitude}"
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray) {
        if (requestCode == REQUEST_CODE) {
            pedirUbicacion()
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}