package com.trpp.compositionofthenumber.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.compositionofthenumber.R
import com.example.compositionofthenumber.databinding.FragmentLevelChooseBinding

class LevelChooseFragment: Fragment() {

    private lateinit var binding: FragmentLevelChooseBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLevelChooseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.summGameButton.setOnClickListener {
            findNavController().navigate(R.id.action_levelChooseFragment_to_difficultyChooseFragment)
        }

    }

}