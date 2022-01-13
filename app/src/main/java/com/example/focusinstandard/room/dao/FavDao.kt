package com.example.focusinstandard.room.dao

import androidx.room.*
import com.example.focusinstandard.room.entity.FavRepos

@Dao
interface FavDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewRepo(favRepos: FavRepos)

    @Query("SELECT * FROM favRepos")
    suspend fun getFavRepos(): List<FavRepos>

    @Query("SELECT EXISTS(SELECT * FROM favRepos where repoId=:repoId)")
    suspend fun checkIfRepoExists(repoId: Int): Boolean

    @Delete
    suspend fun removeFromFavRepos(favRepos: FavRepos)
}