package com.trpp.compositionofthenumber.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
}