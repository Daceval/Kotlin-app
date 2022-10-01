
package com.example.App.fragments

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.App.R
import com.example.App.services.ClimaService

class ClimaFragment : Fragment() {

    var textTemperatura: TextView? = null
    val receiver = object: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val temperatura = intent?.getDoubleExtra(
                ClimaService.EXTRA_TEMPERATURA,
                -200.0) ?: -300.0
            if (temperatura<-100) {
                textTemperatura?.text = "⚠️ Error"
            } else {
                textTemperatura?.text = "$temperatura ⁰C"
            }
        }

    }

    override fun onResume() {
        // registro
        val intentFilter = IntentFilter(ClimaService.ACTION_TEMPERATURA)
        activity?.registerReceiver(receiver, intentFilter)
        super.onResume()
    }

    override fun onPause() {
        // desregistro
        activity?.unregisterReceiver(receiver)
        super.onPause()
    }
    //desde aca inicia el fragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_clima, container, false)
        textTemperatura = rootView.findViewById(R.id.textoTemperatura)

        val editCiudad = rootView.findViewById<EditText>(R.id.editCiudad)

        val btnVerCiudad = rootView.findViewById<Button>(R.id.botonCiudad)
        btnVerCiudad.setOnClickListener {
            val ciudad = editCiudad.text.toString()
            textTemperatura?.text = "⌛️"

            // COMIENZA UN SERVICIO
            val intent = Intent(activity, ClimaService::class.java)
            intent.putExtra("ciudad", ciudad)
            activity?.startService(intent)
        }
        return rootView
    }

}
