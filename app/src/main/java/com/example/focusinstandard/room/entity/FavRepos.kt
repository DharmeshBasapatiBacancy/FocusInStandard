package com.example.focusinstandard.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favRepos")
data class FavRepos(
    @PrimaryKey(autoGenerate = true) val favRepoId: Int = 0,
    val repoId: Int,
    val fullName: String,
    val forksCount: Int,
    val repoLogo: String,
    val repoUrl: String
)
