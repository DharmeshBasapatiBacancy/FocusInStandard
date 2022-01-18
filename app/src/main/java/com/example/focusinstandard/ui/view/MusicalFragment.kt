package com.example.focusinstandard.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.focusinstandard.R
import com.example.focusinstandard.databinding.FragmentMusicalBinding
import com.example.focusinstandard.service.MusicalService

class MusicalFragment : Fragment() {

    private lateinit var binding: FragmentMusicalBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMusicalBinding.inflate(layoutInflater)

        binding.btnStartMusic.setOnClickListener {
            requireActivity().startService(Intent(requireContext(), MusicalService::class.java))
        }

        binding.btnStopMusic.setOnClickListener {
            requireActivity().stopService(Intent(requireContext(), MusicalService::class.java))
        }

        return binding.root
    }


}