package com.example.App.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.App.R
import com.example.App.model.Nota
import com.example.App.model.NotasProvider

//la clase Notasadapter (es una Adapter) hereda de Adapter que recibe(entre <>) la
//clase que representa al ViewHolder
class NotasAdapter : RecyclerView.Adapter<NotasAdapter.NotaViewHolder>() {
    //es comun que la clase de ViewHolder este anidada dentro del adapter
    class NotaViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(nota: Nota) {
            //itemView es una vista en este caso un textView que son cada uno de
            //los rectangulitos del recyclerView
            val txtTitulo = itemView.findViewById<TextView>(R.id.item_nota_titulo)
            txtTitulo.text = nota.titulo
        }
    }
    private var viewHolderListener : ((Int) -> Unit)? = null

    //metodo (getter) hecho por nosotros
    fun setOnViewHolderSelected(listener: (Int) -> Unit) {
        viewHolderListener = listener
    }
    //el recycleView le dice que quiere mostrar el elemento de una determinada
    //posicion (parametro position) y le pasa un ViewHolder (parametro holder)
    //y le pide que lo llene con la informacion.
    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        val nota = NotasProvider.getProvider().getSingleNota(position)
        nota?.let {
            //aca lo llena con la informacion (bind)
            holder.bind(nota)

            //listener si tocan el viewHolder
            holder.itemView.setOnClickListener {
                viewHolderListener?.let {
                    it(position)
                }
            }
            //if (position % 2 == 0) {
            //    holder.itemView.setBackgroundColor(Color.YELLOW)
            //} else {
            //    holder.itemView.setBackgroundColor(Color.WHITE)
            //}

        }
//        if (nota != null) {
//            holder.bind(nota!!)
//        }
    }

    //el recyclerView le va a pedir a Adapater que le devuelva un ViewHolder vacio
    //lo cual lo crea y lo devuelve.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        val inflador = LayoutInflater.from(parent.context)
        val itemNota = inflador.inflate(R.layout.item_nota, parent, false)
        return NotaViewHolder(itemNota)
    }

    //el recyclerView le va a pedir a Adapater por medio de este metodo
    //cuantos elementos va a mostrar en pantalla
    override fun getItemCount(): Int {
        return NotasProvider.getProvider().getAll().count()
    }
}