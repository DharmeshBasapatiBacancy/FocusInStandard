package com.example.focusinstandard.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.focusinstandard.api.models.git.RepoResponseItem
import com.example.focusinstandard.repository.MainRepository
import com.example.focusinstandard.room.DatabaseHelper
import com.example.focusinstandard.room.ReposDatabase
import com.example.focusinstandard.room.entity.FavRepos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainViewModel(
    private val mainRepository: MainRepository,
    private val dbHelper: DatabaseHelper
) : ViewModel() {

    private val TAG = "MainViewModel"

    fun searchRepos(username: String): Flow<PagingData<RepoResponseItem>> {
        return mainRepository.searchRepos(username)
            .cachedIn(viewModelScope)
    }

    private val isRepoExists = MutableLiveData<Boolean>()

    fun checkIfRepoExists(): LiveData<Boolean> = isRepoExists

    fun insertRepoToDB(selectedRepo: RepoResponseItem) {
        viewModelScope.launch(Dispatchers.IO) {

            if (!dbHelper.checkIfRepoExists(selectedRepo.id)) {
                dbHelper.insertNewRepo(
                    FavRepos(
                        repoId = selectedRepo.id, fullName = selectedRepo.full_name,
                        forksCount = selectedRepo.forks, repoLogo = selectedRepo.owner.avatar_url,
                        repoUrl = selectedRepo.svn_url
                    )
                )

            }
        }

    }
}