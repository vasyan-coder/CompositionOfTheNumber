package com.trpp.compositionofthenumber.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.compositionofthenumber.R
import com.example.compositionofthenumber.databinding.FragmentGameResultBinding


class GameResultFragment : Fragment() {
    private lateinit var binding: FragmentGameResultBinding

    private val args by navArgs<GameResultFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.restartButton.setOnClickListener {
            findNavController().navigate(
                GameResultFragmentDirections.actionGameResultFragmentToDifficultyChooseFragment()
            )
        }
        binding.chooseLevelButton.setOnClickListener {
            findNavController().navigate(
                GameResultFragmentDirections.actionGameResultFragmentToLevelChooseFragment()
            )
        }

        binding.textCurrentScore.text =
            getString(R.string.current_score, args.gameResult.countOfRightAnswers.toString())

        if (args.gameResult.winner) {
            binding.imageWinner.setImageResource(R.drawable.win_sticker)
        } else {
            binding.imageWinner.setImageResource(R.drawable.win_sova)
        }

        val progressPercent: Int =
            args.gameResult.countOfRightAnswers * 100 / args.gameResult.countOfQuestions

        binding.progressBar.progress = progressPercent
    }
}