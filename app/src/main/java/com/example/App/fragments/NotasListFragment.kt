package com.example.App.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.App.R
import com.example.App.adapters.NotasAdapter
import com.example.App.model.NotasProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton


class NotasListFragment : Fragment() {
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView =  inflater.inflate(R.layout.fragment_notas_list, container, false)
        rootView.findViewById<FloatingActionButton>(R.id.buttonAgregar).setOnClickListener {
            findNavController().navigate(R.id.action_notasListFragment_to_notasAddFragment)
        }

        //configuracion recyclerView
        val recyclerNotas = rootView.findViewById<RecyclerView>(R.id.recyclerNotas)
        recyclerNotas.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,
            false)
        val adapter = NotasAdapter()
        recyclerNotas.adapter = adapter

        adapter.setOnViewHolderSelected { posicion ->
            val bundle = Bundle()
            bundle.putInt("posicion", posicion)
            findNavController().navigate(R.id.action_notasListFragment_to_notasDetailsFragment, bundle)
        }
        //NotasProvider.getProvider().getAll().forEach {
        //    Log.d("nota", it.toString())
        //}

        NotasProvider.getProvider().registerListener {
          adapter.notifyDataSetChanged()
        }
        return rootView
    }
}

