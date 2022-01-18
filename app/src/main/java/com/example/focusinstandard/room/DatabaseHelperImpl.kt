package com.example.focusinstandard.room

import com.example.focusinstandard.room.entity.FavRepos

class DatabaseHelperImpl(private val reposDatabase: ReposDatabase): DatabaseHelper {
    override suspend fun insertNewRepo(favRepos: FavRepos) {
        reposDatabase.favDao().insertNewRepo(favRepos)
    }

    override suspend fun getFavRepos(): List<FavRepos> {
        return reposDatabase.favDao().getFavRepos()
    }

    override suspend fun checkIfRepoExists(repoId: Int): Boolean {
        return reposDatabase.favDao().checkIfRepoExists(repoId)
    }

    override suspend fun removeFromFavRepos(favRepos: FavRepos) {
        reposDatabase.favDao().removeFromFavRepos(favRepos)
    }
}