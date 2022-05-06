package com.example.test553.domain.model

import com.example.test553.database.ColorDbModel

data class ColorModel(
    val id: Long,
    val name: String,
    val hex: String
) {
    companion object {
        val DEFAULT = with(ColorDbModel.DEFAULT_COLOR)
        { ColorModel(id, name, hex) }
    }
}