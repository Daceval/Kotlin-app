package com.example.App.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notas")
data class Nota(
    val titulo: String,
    @ColumnInfo(name = "nota_texto") var texto: String
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}


