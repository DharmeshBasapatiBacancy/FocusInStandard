package com.example.focusinstandard.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.focusinstandard.api.models.git.RepoResponseItem
import com.example.focusinstandard.api.service.ApiService

private const val INITIAL_PAGE = 1

class MyPagingSource(private val api: ApiService,
                     private val username: String):PagingSource<Int,RepoResponseItem>() {

    override fun getRefreshKey(state: PagingState<Int, RepoResponseItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepoResponseItem> {
        return try {
            val page = params.key ?: INITIAL_PAGE
            val response = api.fetchRepos(username, page, params.loadSize)
            LoadResult.Page(
                data = response,
                prevKey = if (page == INITIAL_PAGE) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}