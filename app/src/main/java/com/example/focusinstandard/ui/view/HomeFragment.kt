package com.example.focusinstandard.ui.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.focusinstandard.api.models.git.RepoResponseItem
import com.example.focusinstandard.api.service.ApiService
import com.example.focusinstandard.databinding.FragmentHomeBinding
import com.example.focusinstandard.repository.MainRepository
import com.example.focusinstandard.room.DatabaseBuilder
import com.example.focusinstandard.ui.adapter.ReposAdapter
import com.example.focusinstandard.ui.adapter.ReposLoadStateAdapter
import com.example.focusinstandard.ui.viewmodel.MainViewModel
import com.example.focusinstandard.utils.ViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var reposAdapter: ReposAdapter
    private val TAG = "HomeFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        setupList()
        setupViewModel()

        return binding.root
    }

    private fun setupList() {
        binding.apply {
            rvPassengers.layoutManager = LinearLayoutManager(requireContext())
            reposAdapter = ReposAdapter() { selectedRepo, shouldOpenRepo ->
                if (shouldOpenRepo) {
                    openRepoInBrowser(selectedRepo.svn_url)
                } else {
                    insertSelectedRepoToDB(selectedRepo)
                }
            }
            rvPassengers.adapter =
                reposAdapter.withLoadStateFooter(footer = ReposLoadStateAdapter())
        }
    }

    private fun openRepoInBrowser(url: String) {
        val openBrowser = Intent(Intent.ACTION_VIEW)
        openBrowser.apply {
            data = Uri.parse(url)
            startActivity(this)
        }
    }

    private fun insertSelectedRepoToDB(it: RepoResponseItem) {
        mainViewModel.insertRepoToDB(it)
    }

    private fun setupViewModel() {

        mainViewModel = ViewModelProvider(
            this, ViewModelFactory(
                MainRepository(ApiService.create()), DatabaseBuilder.getDBInstance(requireContext())
            )
        )[MainViewModel::class.java]

        lifecycleScope.launch {
            mainViewModel.searchRepos("google").collect {
                reposAdapter.submitData(it)
            }
        }
    }

}