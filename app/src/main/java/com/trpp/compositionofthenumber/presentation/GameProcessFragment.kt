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
        viewModel.question.observe(viewLifecycleOwner){
            binding.textNumber.text = it.sum.toString()
            binding.textFirstPartNum.text = it.visibleNumber.toString()
            binding.tvOption1.text = it.options[0].toString()
            binding.tvOption2.text = it.options[1].toString()
            binding.tvOption3.text = it.options[2].toString()
            binding.tvOption4.text = it.options[3].toString()
            binding.tvOption5.text = it.options[4].toString()
            binding.tvOption6.text = it.options[5].toString()
        }

    }
}