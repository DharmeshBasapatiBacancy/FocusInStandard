package com.example.focusinstandard.room

import android.content.Context
import androidx.room.Room

object DatabaseBuilder {

    private var dbInstance: ReposDatabase? = null

    private fun buildReposDB(context: Context): ReposDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ReposDatabase::class.java,
            "ReposDatabase"
        ).build()
    }

    fun getDBInstance(context: Context): ReposDatabase {
        if(dbInstance == null){
            synchronized(ReposDatabase::class){
                dbInstance = buildReposDB(context)
            }
        }
        return dbInstance!!
    }

}