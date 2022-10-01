package com.example.App.model

import androidx.room.*

@Dao
interface NotasDao {
    @Query("SELECT * FROM Notas")
    fun getAll() : List<Nota>

    @Query("SELECT * FROM Notas WHERE id = :notaId LIMIT 1")
    fun getById(notaId: Int ): Nota

    @Insert
    fun insert(vararg notas: Nota)

    @Query("DELETE FROM Notas")
    fun deleteAll()

    @Delete
    fun delete(nota: Nota)

    @Update
    fun update(nota:Nota)
}