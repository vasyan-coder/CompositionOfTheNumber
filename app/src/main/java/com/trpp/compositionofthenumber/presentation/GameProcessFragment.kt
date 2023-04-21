package com.trpp.compositionofthenumber.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.compositionofthenumber.databinding.FragmentGameProcessBinding


class GameProcessFragment : Fragment() {
    private lateinit var binding: FragmentGameProcessBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameProcessBinding.inflate(inflater, container, false)
        return binding.root
    }
}