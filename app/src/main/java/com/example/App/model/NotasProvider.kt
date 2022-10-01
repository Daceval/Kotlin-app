package com.example.App.model

import androidx.room.Room
import com.example.App.App

class NotasProvider {
    companion object {
        private val instance = NotasProvider()
        fun getProvider(): NotasProvider {
            return  instance
        }
    }
    private var notas = mutableListOf<Nota>()

    private var db: AppDatabase = Room.databaseBuilder(
        App.context,
        AppDatabase::class.java, "appTransaporte"
    ).allowMainThreadQueries().build()

   init {
      notas = db.notasDao().getAll().toMutableList()
    }
    private val listeners = mutableListOf<()->Unit>()

    fun registerListener(listener : ()->Unit) {
        listeners.add(listener)
    }

    fun deleteNota(nota: Nota) {
        db.notasDao().delete(nota)
        notas.remove(nota)
    }

    fun addNota(nota:Nota) {
        notas.add(nota)
        listeners.forEach {
            it.invoke()
        }
        db.notasDao().insert(nota)
    }

    fun getAll() : MutableList<Nota> {
        return notas
    }

    fun getSingleNota(posicion : Int) : Nota? {
        if (posicion >= 0) {
            return notas[posicion]
        } else {
            return null
        }
    }
}