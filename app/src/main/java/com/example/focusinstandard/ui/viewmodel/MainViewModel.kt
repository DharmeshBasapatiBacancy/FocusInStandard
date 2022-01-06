package com.example.focusinstandard.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.focusinstandard.api.models.git.RepoResponseItem
import com.example.focusinstandard.repository.MainRepository
import kotlinx.coroutines.flow.Flow

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun searchRepos(username: String): Flow<PagingData<RepoResponseItem>> {
        return mainRepository.searchRepos(username)
            .cachedIn(viewModelScope)
    }

}