package com.trpp.compositionofthenumber.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.compositionofthenumber.R
import com.example.compositionofthenumber.databinding.FragmentStartFrameBinding

class StartFrameFragment: Fragment() {

    private lateinit var binding: FragmentStartFrameBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartFrameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.startGameButton.setOnClickListener{
            findNavController().navigate(R.id.action_startFrameFragment_to_levelChooseFragment)
        }
    }

}