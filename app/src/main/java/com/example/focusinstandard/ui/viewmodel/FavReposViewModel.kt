package com.example.focusinstandard.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.focusinstandard.room.DatabaseHelper
import com.example.focusinstandard.room.ReposDatabase
import com.example.focusinstandard.room.entity.FavRepos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavReposViewModel(private val dbHelper: DatabaseHelper): ViewModel() {

    private val favRepos = MutableLiveData<List<FavRepos>>()

    fun getFavRepos() : LiveData<List<FavRepos>> = favRepos

    fun fetchFavReposFromFB(){

        viewModelScope.launch(Dispatchers.IO) {

            favRepos.postValue(dbHelper.getFavRepos())

        }

    }

    fun removeRepoFromDB(favRepos: FavRepos){

        viewModelScope.launch(Dispatchers.IO) {

            dbHelper.removeFromFavRepos(favRepos)
            fetchFavReposFromFB()
        }

    }

}