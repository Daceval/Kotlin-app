package com.example.App.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.App.R
import com.example.App.model.Nota
import com.example.App.model.NotasProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NotasDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_notas_details, container, false)

        val position = arguments?.getInt("posicion")
        if (position == null) {
            //TODO implementar que hacer si la posicion no esta
        }
        val nota: Nota? = NotasProvider.getProvider().getSingleNota(position!!)

        rootView.findViewById<TextView>(R.id.textoDetalle).text = nota?.titulo
        rootView.findViewById<TextView>(R.id.textoDescripcion).text = nota?.texto
        rootView.findViewById<FloatingActionButton>(R.id.botonEliminar).setOnClickListener {
            AlertDialog.Builder(activity).setTitle("Eliminacion de la Nota")
                .setMessage("La nota se eliminara permanentemente")
                .setPositiveButton("Si") { _,_ ->
                    NotasProvider.getProvider().deleteNota(nota!!)
                    findNavController().navigateUp()

                }
                .setNegativeButton("No", null)
                .show()
        }
        return rootView
    }
}