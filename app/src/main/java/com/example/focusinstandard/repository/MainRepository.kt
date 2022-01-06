package com.example.focusinstandard.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.focusinstandard.api.service.ApiService
import com.example.focusinstandard.paging.MyPagingSource

class MainRepository(private val apiService: ApiService) {

    fun searchRepos(username: String) = Pager(
        pagingSourceFactory = { MyPagingSource(apiService, username) },
        config = PagingConfig(
            pageSize = 10
        )
    ).flow
}