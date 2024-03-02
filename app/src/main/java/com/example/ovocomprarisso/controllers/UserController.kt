package com.example.ovocomprarisso.controllers

import android.content.Context
import com.example.ovocomprarisso.database.DatabaseRoom
import com.example.ovocomprarisso.models.UserEntity
import com.example.ovocomprarisso.repository.UserDAO
import org.mindrot.jbcrypt.BCrypt

class UserController (private var context: Context){
    private lateinit var dao: UserDAO

    init {
        dao = DatabaseRoom.getDatabase(context).userDAO()
    }

    fun save(username: String, password: String): Boolean{
        if(username.isNullOrBlank() || password.isNullOrBlank()){
            return false
        }
        if(dao.getUser(username) == null){
            dao.save(UserEntity(username, hashPassword(password)))
            return true
        }
        return false
    }

    fun getUserLogin(username: String, password: String): Boolean{
        val user = dao.getUser(username)
        if(user == null) return false
        else if(checkPassword(user?.password, password)) return true
        return false
    }

    fun getUser(username: String): UserEntity?{
        return dao.getUser(username)
    }

    private fun checkPassword(dbPw: String?, password: String): Boolean{
        return BCrypt.checkpw(password, dbPw)
    }

    private fun hashPassword(password: String): String{
        return BCrypt.hashpw(password, BCrypt.gensalt())
    }
}