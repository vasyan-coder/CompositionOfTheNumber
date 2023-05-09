package com.trpp.compositionofthenumber.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.compositionofthenumber.R
import com.example.compositionofthenumber.databinding.FragmentDifficultyChooseBinding
import com.trpp.compositionofthenumber.data.GameRepositoryImpl
import com.trpp.compositionofthenumber.domain.entity.Level

class DifficultyChooseFragment : Fragment() {

    private lateinit var binding: FragmentDifficultyChooseBinding

    private val repository = GameRepositoryImpl

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
            findNavController().navigate(
                DifficultyChooseFragmentDirections.actionDifficultyChooseFragmentToGameProcessFragment(
                    repository.getGameSettings(Level.EASY)
                )
            )
        }
        binding.mediumGameTypeButton.setOnClickListener {
            findNavController().navigate(
                DifficultyChooseFragmentDirections.actionDifficultyChooseFragmentToGameProcessFragment(
                    repository.getGameSettings(Level.NORMAL)
                )
            )
        }
        binding.hardGameTypeButton.setOnClickListener {
            findNavController().navigate(
                DifficultyChooseFragmentDirections.actionDifficultyChooseFragmentToGameProcessFragment(
                    repository.getGameSettings(Level.HARD)
                )
            )
        }
    }
}