package com.example.ovocomprarisso.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ovocomprarisso.models.ItemEntity

@Dao
interface ItemsDAO {
    @Insert
    fun save(item: ItemEntity)

    @Query("SELECT * FROM items WHERE user_id=:userId;")
    fun getAllItems(userId: Long) : List<ItemEntity>
}