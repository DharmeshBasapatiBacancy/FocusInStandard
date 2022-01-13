package com.example.focusinstandard.ui.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.focusinstandard.R
import com.example.focusinstandard.api.service.ApiService
import com.example.focusinstandard.databinding.FragmentFavoriteBinding
import com.example.focusinstandard.repository.MainRepository
import com.example.focusinstandard.room.DatabaseBuilder
import com.example.focusinstandard.room.DatabaseHelperImpl
import com.example.focusinstandard.room.entity.FavRepos
import com.example.focusinstandard.ui.adapter.FavReposAdapter
import com.example.focusinstandard.ui.viewmodel.FavReposViewModel
import com.example.focusinstandard.ui.viewmodel.MainViewModel
import com.example.focusinstandard.utils.ViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FavoriteFragment : Fragment() {

    private lateinit var favReposAdapter: FavReposAdapter
    private lateinit var favReposViewModel: FavReposViewModel
    private lateinit var binding: FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(layoutInflater)
        setupList()
        setupViewModel()
        return binding.root
    }

    private fun setupList() {
        binding.apply {
            rvFavRepos.layoutManager = LinearLayoutManager(requireContext())
            favReposAdapter = FavReposAdapter(arrayListOf(), {
                removeRepoFromDB(it)
            }, {
                openRepoInBrowser(it.repoUrl)
            })
            rvFavRepos.adapter = favReposAdapter
        }
    }

    private fun openRepoInBrowser(url: String) {
        val openBrowser = Intent(Intent.ACTION_VIEW)
        openBrowser.apply {
            data = Uri.parse(url)
            startActivity(this)
        }
    }

    private fun setupViewModel() {

        favReposViewModel = ViewModelProvider(
            this, ViewModelFactory(
                MainRepository(ApiService.create()), DatabaseHelperImpl(DatabaseBuilder.getDBInstance(requireContext()))
            )
        )[FavReposViewModel::class.java]

        favReposViewModel.fetchFavReposFromFB()

        favReposViewModel.getFavRepos().observe(requireActivity(), {

            if (it.isEmpty()) {
                Toast.makeText(requireContext(), "No Favorites Yet.", Toast.LENGTH_SHORT)
                    .show()
            } else {
                updateFavReposList(it)
            }

        })
    }

    private fun removeRepoFromDB(favRepos: FavRepos) {
        favReposViewModel.removeRepoFromDB(favRepos)
    }

    private fun updateFavReposList(it: List<FavRepos>) {
        favReposAdapter.updateFavReposList(it)
    }
}