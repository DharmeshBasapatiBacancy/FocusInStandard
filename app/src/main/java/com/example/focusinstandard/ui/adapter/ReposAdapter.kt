package com.example.focusinstandard.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.focusinstandard.api.models.git.RepoResponseItem
import com.example.focusinstandard.databinding.RowItemPassengersBinding

class ReposAdapter :
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

    class PassengersViewHolder(private val rowItemPassengersBinding: RowItemPassengersBinding) :
        RecyclerView.ViewHolder(rowItemPassengersBinding.root) {
        fun bind(repoResponseItem: RepoResponseItem) = with(rowItemPassengersBinding) {
            tvPassengerName.text = repoResponseItem.full_name
            tvTripsCount.text = "Forks - ${repoResponseItem.forks}"
            Glide.with(itemView).load(repoResponseItem.owner.avatar_url)
                .into(imgAirline)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PassengersViewHolder(
        RowItemPassengersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: PassengersViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

}