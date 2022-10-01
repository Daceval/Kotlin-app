package com.example.App.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.example.App.R
import com.example.App.model.Nota
import com.example.App.model.NotasProvider


class NotasAddFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_notas_add, container, false)

        rootView.findViewById<Button>(R.id.botonNotaAgregar).setOnClickListener {
            val titulo = rootView.findViewById<EditText>(R.id.tituloNota).text.toString()
            val descripcion = rootView.findViewById<EditText>(R.id.descripcionNota).text.toString()
            NotasProvider.getProvider().addNota((Nota(titulo, descripcion)))
            findNavController().navigateUp()
        }
        return rootView
    }
}