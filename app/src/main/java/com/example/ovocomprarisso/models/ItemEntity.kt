package com.example.ovocomprarisso.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "items")
class ItemEntity {
    constructor()

    constructor(item: String, userId: Long, type: String){
        this.item = item
        this.userId = userId
        this.type = type
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long=0L

    @ColumnInfo(name = "item")
    var item: String = ""

    @ColumnInfo(name = "type")
    var type: String = ""

    @ColumnInfo(name = "user_id")
    var userId: Long = 0L
}