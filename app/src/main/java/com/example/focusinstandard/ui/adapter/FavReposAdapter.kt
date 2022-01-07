package com.example.focusinstandard.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.focusinstandard.databinding.RowItemReposBinding
import com.example.focusinstandard.room.entity.FavRepos

class FavReposAdapter(
    private var reposList: List<FavRepos>,
    val deleteRepo: (FavRepos) -> Unit,
    val openRepo: (FavRepos) -> Unit
) :
    RecyclerView.Adapter<FavReposAdapter.ViewHolder>() {

    class ViewHolder(val rowItemReposBinding: RowItemReposBinding) :
        RecyclerView.ViewHolder(rowItemReposBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        RowItemReposBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(reposList[position]) {
                rowItemReposBinding.apply {
                    imgDelete.visibility = View.VISIBLE

                    tvGitRepoName.text = fullName
                    tvForksCount.text = "Forks - $forksCount"
                    Glide.with(itemView).load(repoLogo)
                        .into(imgRepoLogo)
                    imgDelete.setOnClickListener {
                        deleteRepo(reposList[position])
                    }
                    btnVisitRepo.setOnClickListener {
                        openRepo(reposList[position])
                    }

                }
            }
        }
    }

    override fun getItemCount() = reposList.size

    fun updateFavReposList(list: List<FavRepos>) {
        reposList = list
        notifyDataSetChanged()
    }
}