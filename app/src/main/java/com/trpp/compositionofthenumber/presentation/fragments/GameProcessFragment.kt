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
import androidx.navigation.fragment.navArgs
import com.example.compositionofthenumber.R
import com.example.compositionofthenumber.databinding.FragmentGameProcessBinding
import com.trpp.compositionofthenumber.domain.entity.Level
import com.trpp.compositionofthenumber.presentation.viewmodels.GameProcessViewModel
import com.trpp.compositionofthenumber.presentation.viewmodels.GameViewModelFactory


class GameProcessFragment : Fragment() {

    private val args by navArgs<GameProcessFragmentArgs>()

    private lateinit var binding: FragmentGameProcessBinding
    private val viewModelFactory by lazy {
        GameViewModelFactory(
            args.gameSettings.gameLevel,
            requireActivity().application,
            args.gameSettings.gameType
        )
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
            when (args.gameSettings.gameLevel) {
                Level.EASY -> R.color.text_color
                Level.NORMAL -> R.color.text_color
                Level.HARD -> R.color.white
                else -> {
                    throw RuntimeException("Not expected difficulty: ${args.gameSettings.gameLevel}")
                }
            }
        } else {
            when (args.gameSettings.gameLevel) {
                Level.EASY -> R.color.text_color
                Level.NORMAL -> R.color.text_color
                Level.HARD -> R.color.white
                else -> {
                    throw RuntimeException("Not expected difficulty: ${args.gameSettings.gameLevel}")
                }
            }
        }
        return ContextCompat.getColor(requireContext(), coloResId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setColorScheme()

        viewModel.formattedTime.observe(viewLifecycleOwner) {
            binding.timer.text = it.toString()
        }
        viewModel.question.observe(viewLifecycleOwner) {
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

    private fun setColorScheme() {
        when (args.gameSettings.gameLevel) {
            Level.EASY -> {
                binding.background
                    .setBackgroundColor(requireContext().getColor(R.color.easy_game_mode))
                binding.tvOption1.background.setTint(requireContext().getColor(R.color.white))
                binding.tvOption2.background.setTint(requireContext().getColor(R.color.white))
                binding.tvOption3.background.setTint(requireContext().getColor(R.color.white))
                binding.tvOption4.background.setTint(requireContext().getColor(R.color.white))
                binding.tvOption5.background.setTint(requireContext().getColor(R.color.white))
                binding.tvOption6.background.setTint(requireContext().getColor(R.color.white))

                binding.textNumber.setBackgroundResource(R.drawable.circul_border_game_elements_easy)
                binding.textFirstPartNum.background.setTint(requireContext().getColor(R.color.white))
                binding.unknownNum.background.setTint(requireContext().getColor(R.color.white))

                binding.timer.setTextColor(requireContext().getColor(R.color.text_color))
            }

            Level.NORMAL -> {
                binding.background
                    .setBackgroundColor(requireContext().getColor(R.color.medium_game_mode))
                binding.tvOption1.background.setTint(requireContext().getColor(R.color.dark_beige_button))
                binding.tvOption2.background.setTint(requireContext().getColor(R.color.light_beige_button))
                binding.tvOption3.background.setTint(requireContext().getColor(R.color.dark_beige_button))
                binding.tvOption4.background.setTint(requireContext().getColor(R.color.light_beige_button))
                binding.tvOption5.background.setTint(requireContext().getColor(R.color.dark_beige_button))
                binding.tvOption6.background.setTint(requireContext().getColor(R.color.light_beige_button))

                binding.textNumber.setBackgroundResource(R.drawable.circul_border_game_elements_medium)
                binding.textFirstPartNum.background.setTint(requireContext().getColor(R.color.white))
                binding.unknownNum.background.setTint(requireContext().getColor(R.color.white))

                binding.timer.setTextColor(requireContext().getColor(R.color.text_color))
            }

            Level.HARD -> {
                binding.background
                    .setBackgroundColor(requireContext().getColor(R.color.hard_game_mode))
                binding.tvOption1.background.setTint(requireContext().getColor(R.color.dark_beige_button))
                binding.tvOption2.background.setTint(requireContext().getColor(R.color.dark_beige_button))
                binding.tvOption3.background.setTint(requireContext().getColor(R.color.dark_beige_button))
                binding.tvOption4.background.setTint(requireContext().getColor(R.color.dark_beige_button))
                binding.tvOption5.background.setTint(requireContext().getColor(R.color.dark_beige_button))
                binding.tvOption6.background.setTint(requireContext().getColor(R.color.dark_beige_button))

                binding.textNumber.setBackgroundResource(R.drawable.circul_border_game_elements_hard)
                binding.textFirstPartNum.background.setTint(requireContext().getColor(R.color.light_beige_button))
                binding.unknownNum.background.setTint(requireContext().getColor(R.color.light_beige_button))

                binding.timer.setTextColor(requireContext().getColor(R.color.light_beige_button))
            }

            else -> {
                throw RuntimeException("Not expected difficulty: ${args.gameSettings.gameLevel}")
            }
        }
    }
}