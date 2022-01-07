package com.example.focusinstandard.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.focusinstandard.api.models.git.RepoResponseItem
import com.example.focusinstandard.databinding.RowItemReposBinding

class ReposAdapter(private val onItemClick : (RepoResponseItem,Boolean) -> Unit) :
    PagingDataAdapter<RepoResponseItem, ReposAdapter.PassengersViewHolder>(COMPARATOR) {

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<RepoResponseItem>() {
            override fun areItemsTheSame(
                oldItem: RepoResponseItem,
                newItem: RepoResponseItem
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: RepoResponseItem,
                newItem: RepoResponseItem
            ): Boolean =
                oldItem == newItem
        }
    }

    class PassengersViewHolder(private val rowItemPassengersBinding: RowItemReposBinding) :
        RecyclerView.ViewHolder(rowItemPassengersBinding.root) {
        fun bind(repoResponseItem: RepoResponseItem, onItemClick: (RepoResponseItem,Boolean) -> Unit) = with(rowItemPassengersBinding) {
            imgFavorite.visibility = View.VISIBLE

            tvGitRepoName.text = repoResponseItem.full_name
            tvForksCount.text = "Forks - ${repoResponseItem.forks}"
            Glide.with(itemView).load(repoResponseItem.owner.avatar_url)
                .into(imgRepoLogo)
            imgFavorite.setOnClickListener {
                onItemClick(repoResponseItem,false)
            }
            btnVisitRepo.setOnClickListener {
                onItemClick(repoResponseItem,true)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PassengersViewHolder(
        RowItemReposBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: PassengersViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it,onItemClick)

        }
    }

}