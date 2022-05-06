package com.example.test553.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ContactDao {

    @Query("SELECT * FROM ContactDbModel")
    fun getAllSync(): List<ContactDbModel>

    @Query("SELECT * FROM ContactDbModel WHERE id IN (:contactIds)")
    fun getContactsByIdsSync(contactIds: List<Long>): List<ContactDbModel>

    @Query("SELECT * FROM ContactDbModel WHERE id LIKE :id")
    fun findByIdSync(id: Long): ContactDbModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contactDbModel: ContactDbModel)

    @Insert
    fun insertAll(vararg contactDbModel: ContactDbModel)

    @Query("DELETE FROM ContactDbModel WHERE id LIKE :id")
    fun delete(id: Long)

    @Query("DELETE FROM ContactDbModel WHERE id IN (:contactIds)")
    fun delete(contactIds: List<Long>)
}