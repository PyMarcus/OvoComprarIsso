package com.example.ovocomprarisso.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.ovocomprarisso.models.ItemEntity

@Dao
interface ItemsDAO {
    @Insert
    fun save(item: ItemEntity)

    @Query("DELETE from items WHERE id=:id;")
    fun remove(id: Long)

    @Query("SELECT * FROM items WHERE user_id=:userId;")
    fun getAllItems(userId: Long) : List<ItemEntity>
}