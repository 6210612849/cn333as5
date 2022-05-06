package com.example.test553.database
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ContactDbModel(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "Number") val number: String,
    @ColumnInfo(name = "can_be_checked_off") val canBeCheckedOff: Boolean,
    @ColumnInfo(name = "is_checked_off") val isCheckedOff: Boolean,
    @ColumnInfo(name = "color_id") val colorId: Long,
    @ColumnInfo(name = "in_trash") val isInTrash: Boolean,


    ){
    companion object {
        val DEFAULT_CONTACTS = listOf(
            ContactDbModel(1, "Weerapong", "Prepare sample project","0863276130", false, false, 1, false),
            ContactDbModel(2, "John smith", "Pay by tomorrow", "0863276130", false, false, 2,false),
            ContactDbModel(3, "test", "Milk, eggs, salt, flour...", "0863276130", false, false, 3,false),
            ContactDbModel(4, "Workout", "Running, push ups, pull ups, squats...","0863276130", false, false, 4, false),

        )
    }}