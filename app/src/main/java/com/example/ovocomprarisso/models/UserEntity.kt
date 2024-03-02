package com.example.ovocomprarisso.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")
class UserEntity {
    constructor()

    constructor(username: String, password: String){
        this.username = username
        this.password = password
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long=0L

    @ColumnInfo(name = "username")
    var username: String = ""

    @ColumnInfo(name = "password")
    var password: String = ""
}