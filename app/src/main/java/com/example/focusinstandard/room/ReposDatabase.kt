package com.example.focusinstandard.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.focusinstandard.room.dao.FavDao
import com.example.focusinstandard.room.entity.FavRepos

@Database(entities = [FavRepos::class], version = 1)
abstract class ReposDatabase: RoomDatabase() {

    abstract fun favDao():  FavDao
}