package com.example.ovocomprarisso.controllers

import android.content.Context
import com.example.ovocomprarisso.database.DatabaseRoom
import com.example.ovocomprarisso.models.ItemEntity
import com.example.ovocomprarisso.repository.ItemsDAO

class ItemController(private var context: Context) {
    private lateinit var dao: ItemsDAO

    init {
        dao = DatabaseRoom.getDatabase(context).itemsDAO()
    }

    fun save(product: String, category: String, userId: Long) {
        dao.save(ItemEntity(product, userId, category))
    }

    fun getAll(userId: Long): List<ItemEntity>{
        return dao.getAllItems(userId)
    }

    fun remove(item: ItemEntity){
        dao.remove(item.id)
    }
}