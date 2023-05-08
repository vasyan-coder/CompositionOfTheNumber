package com.trpp.compositionofthenumber.presentation.fragments

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.compositionofthenumber.databinding.FragmentGameProcessBinding
import com.trpp.compositionofthenumber.domain.entity.Level
import com.trpp.compositionofthenumber.presentation.viewmodels.GameProcessViewModel
import com.trpp.compositionofthenumber.presentation.viewmodels.GameViewModelFactory


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
    private fun getColorByState(goodState: Boolean): Int {
        val coloResId = if (goodState) {
            android.R.color.holo_green_light
        } else {
            android.R.color.holo_red_dark
        }
        return ContextCompat.getColor(requireContext(), coloResId)
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
        binding.tvOption1.setOnClickListener {
            viewModel.chooseAnswer(binding.tvOption1.text.toString().toInt())
        }
        binding.tvOption2.setOnClickListener {
            viewModel.chooseAnswer(binding.tvOption2.text.toString().toInt())
        }
        binding.tvOption3.setOnClickListener {
            viewModel.chooseAnswer(binding.tvOption3.text.toString().toInt())
        }
        binding.tvOption4.setOnClickListener {
            viewModel.chooseAnswer(binding.tvOption4.text.toString().toInt())
        }
        binding.tvOption5.setOnClickListener {
            viewModel.chooseAnswer(binding.tvOption5.text.toString().toInt())
        }
        binding.tvOption6.setOnClickListener {
            viewModel.chooseAnswer(binding.tvOption6.text.toString().toInt())
        }
        viewModel.progressAnswers.observe(viewLifecycleOwner) {
            binding.progressText.text = it
        }
        viewModel.percentOfRightAnswers.observe(viewLifecycleOwner) {
            binding.progressBar.setProgress(it, true)
        }
        viewModel.enoughCountOfRightAnswers.observe(viewLifecycleOwner) {
            binding.progressText.setTextColor(getColorByState(it))
        }
        viewModel.enoughPercentOfRightAnswers.observe(viewLifecycleOwner) {
            val color = getColorByState(it)
            binding.progressBar.progressTintList = ColorStateList.valueOf(color)
        }
        viewModel.gameResult.observe(viewLifecycleOwner) {
            findNavController().navigate(
                GameProcessFragmentDirections.actionGameProcessFragmentToGameResultFragment(
                    it
                )
            )
        }
    }
}