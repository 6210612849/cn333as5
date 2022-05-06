package com.example.test553.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ColorDbModel(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "hex") val hex: String,
    @ColumnInfo(name = "name") val name: String
) {
    companion object {
        val DEFAULT_COLORS = listOf(
            ColorDbModel(1, "#FFFFFF", "anoy"),
            ColorDbModel(2, "#E57373", "Home"),
            ColorDbModel(3, "#F06292", "BF/GF"),
            ColorDbModel(4, "#CE93D8", "FRIEND"),
            ColorDbModel(5, "#2196F3", "Family"),
            ColorDbModel(6, "#00ACC1", "Work"),
            ColorDbModel(7, "#26A69A", "BestFriend"),
            ColorDbModel(8, "#4CAF50", "Test"),
            ColorDbModel(9, "#8BC34A", "Test"),
            ColorDbModel(10, "#CDDC39", "Lime"),
            ColorDbModel(11, "#FFEB3B", "Yellow"),
            ColorDbModel(12, "#FF9800", "Orange"),
            ColorDbModel(13, "#BCAAA4", "Brown"),
            ColorDbModel(14, "#9E9E9E", "Gray")
        )
        val DEFAULT_COLOR = DEFAULT_COLORS[0]
    }
}