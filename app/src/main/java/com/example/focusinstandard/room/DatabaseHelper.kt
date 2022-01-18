package com.example.focusinstandard.room

import com.example.focusinstandard.room.entity.FavRepos

interface DatabaseHelper {

    suspend fun insertNewRepo(favRepos: FavRepos)

    suspend fun getFavRepos(): List<FavRepos>

    suspend fun checkIfRepoExists(repoId: Int): Boolean

    suspend fun removeFromFavRepos(favRepos: FavRepos)
}