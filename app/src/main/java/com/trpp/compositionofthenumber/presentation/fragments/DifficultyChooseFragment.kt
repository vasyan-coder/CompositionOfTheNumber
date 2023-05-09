package com.trpp.compositionofthenumber.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.compositionofthenumber.R
import com.example.compositionofthenumber.databinding.FragmentDifficultyChooseBinding

class DifficultyChooseFragment : Fragment() {

    private lateinit var binding: FragmentDifficultyChooseBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDifficultyChooseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.easyGameTypeButton.setOnClickListener {
            findNavController().navigate(R.id.action_difficultyChooseFragment_to_gameProcessFragment)
        }
    }
}