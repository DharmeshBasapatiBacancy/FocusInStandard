package com.example.focusinstandard.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.focusinstandard.repository.MainRepository
import com.example.focusinstandard.room.DatabaseHelper
import com.example.focusinstandard.room.ReposDatabase
import com.example.focusinstandard.ui.viewmodel.FavReposViewModel
import com.example.focusinstandard.ui.viewmodel.MainViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val mainRepository: MainRepository,private val dbHelper: DatabaseHelper): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(mainRepository,dbHelper) as T
        }
        if(modelClass.isAssignableFrom(FavReposViewModel::class.java)){
            return FavReposViewModel(dbHelper) as T
        }
        throw IllegalArgumentException("Unknown CLass Name")
    }
}