package com.example.App.services
import android.app.IntentService
import android.content.Intent
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

/*
IntentService:
 1) Después de hacer su tarea, se elimina solo (onDestroy)
 2) Genera un thread por nosotros automáticamente
 */

class ClimaService : IntentService("Clima Service") {

    companion object {
        const val TAG = "ClimaService"
        const val ACTION_TEMPERATURA = "com.eldars.transporte.temperatura_lista"
        const val EXTRA_TEMPERATURA = "temp"
    }

    // Main Thread
    override fun onCreate() {
        Log.d(TAG, "onCreate")
        super.onCreate()
    }

    // Worker Thread
    override fun onHandleIntent(intent: Intent?) {
        val queue = Volley.newRequestQueue(this)
        val ciudad = intent?.getStringExtra("ciudad") ?: "buenos aires"
        val url = "https://api.openweathermap.org/data/2.5/weather?q=$ciudad&appid=1300a84c4bfb3855cf5912eca503d8a0&units=metric"


        // Request a string response from the provided URL.

        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            {
                val temperatura: Double = it.getJSONObject("main").getDouble("temp")

                val broadcastIntent = Intent(ACTION_TEMPERATURA)
                broadcastIntent.putExtra(EXTRA_TEMPERATURA, temperatura)
                sendBroadcast(broadcastIntent)
            },
            {

            })

        // Add the request to the RequestQueue.
        queue.add(request)
    }


    // Main Thread
    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        super.onDestroy()
    }


}