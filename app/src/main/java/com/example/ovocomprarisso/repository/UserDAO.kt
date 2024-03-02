package com.example.ovocomprarisso.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ovocomprarisso.models.UserEntity

@Dao
interface UserDAO {
    @Insert
    fun save(user: UserEntity)

    @Query("SELECT * FROM users WHERE username=:username limit 1;")
    fun getUser(username: String): UserEntity?
}