package com.trpp.compositionofthenumber.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.compositionofthenumber.databinding.FragmentGameProcessBinding
import com.trpp.compositionofthenumber.domain.entity.Level


class GameProcessFragment : Fragment() {
    private lateinit var binding: FragmentGameProcessBinding
    private val viewModelFactory by lazy {
        GameViewModelFactory(Level.EASY, requireActivity().application)
    }

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[GameProcessViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameProcessBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.formattedTime.observe(viewLifecycleOwner){
            binding.timer.text = it.toString()
        }
    }
}