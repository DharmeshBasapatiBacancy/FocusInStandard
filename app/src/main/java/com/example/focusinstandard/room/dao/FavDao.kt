package com.example.focusinstandard.room.dao

import androidx.room.*
import com.example.focusinstandard.room.entity.FavRepos

@Dao
interface FavDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewRepo(favRepos: FavRepos)

    @Query("SELECT * FROM favRepos")
    fun getFavRepos(): List<FavRepos>

    @Query("SELECT EXISTS(SELECT * FROM favRepos where repoId=:repoId)")
    fun checkIfRepoExists(repoId: Int): Boolean

    @Delete
    fun removeFromFavRepos(favRepos: FavRepos)
}