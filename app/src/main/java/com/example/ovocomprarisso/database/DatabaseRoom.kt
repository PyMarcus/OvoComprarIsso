package com.example.ovocomprarisso.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ovocomprarisso.models.ItemEntity
import com.example.ovocomprarisso.models.UserEntity
import com.example.ovocomprarisso.repository.ItemsDAO
import com.example.ovocomprarisso.repository.UserDAO


@Database(entities = [UserEntity::class, ItemEntity::class], version = 2, exportSchema = false)
abstract class DatabaseRoom(): RoomDatabase() {

    abstract fun userDAO(): UserDAO
    abstract fun itemsDAO(): ItemsDAO

    companion object{
        private lateinit var INSTANCE: DatabaseRoom

        fun getDatabase(context: Context): DatabaseRoom{
            if(!::INSTANCE.isInitialized){
                synchronized(DatabaseRoom::class){
                    INSTANCE = Room.
                            databaseBuilder(context, DatabaseRoom::class.java, "database_room")
                        .allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }
    }

}