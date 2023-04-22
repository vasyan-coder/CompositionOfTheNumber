package com.trpp.compositionofthenumber.presentation

import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trpp.compositionofthenumber.data.GameRepositoryImpl
import com.trpp.compositionofthenumber.domain.entity.GameSettings
import com.trpp.compositionofthenumber.domain.entity.Level
import com.trpp.compositionofthenumber.domain.entity.Question
import com.trpp.compositionofthenumber.domain.usecases.GenerateQuestionUseCase
import com.trpp.compositionofthenumber.domain.usecases.GetGameSettingsUseCase


class GameProcessViewModel(
    private val context: Application,
    private val level: Level
) : ViewModel() {
    private lateinit var gameSettings: GameSettings
    private val repository = GameRepositoryImpl

    private val generateQuestionUseCase = GenerateQuestionUseCase(repository)
    private val getGameSettingsUseCase = GetGameSettingsUseCase(repository)
    private val _formattedTime = MutableLiveData<String>()
    val formattedTime: LiveData<String>
        get() = _formattedTime
    private var timer: CountDownTimer? = null

    companion object {

        private const val MILLIS_IN_SECONDS = 1000
        private const val SECONDS_IN_MINUTES = 60

    }

    private val _question = MutableLiveData<Question>()
    val question: LiveData<Question>
        get() = _question

    init {
        getGameSettings()
        generateQuestion()
        startTimer()
    }

    private fun formatTime(millisUntilFinished: Long): String {
        val seconds = millisUntilFinished / MILLIS_IN_SECONDS
        val minutes = seconds / SECONDS_IN_MINUTES
        val leftSeconds = seconds - (minutes * SECONDS_IN_MINUTES)
        return String.format("%02d:%02d", minutes, leftSeconds)
    }

    private fun startTimer() {
        timer = object : CountDownTimer(
            (gameSettings.gameTimeInSeconds * MILLIS_IN_SECONDS).toLong(),
            MILLIS_IN_SECONDS.toLong()
        ) {
            override fun onTick(millisUntilFinished: Long) {
                _formattedTime.value = formatTime(millisUntilFinished)
            }

            override fun onFinish() {

            }
        }
        timer?.start()
    }

    private fun getGameSettings() {
        this.gameSettings = getGameSettingsUseCase(level)
        //_minPercent.value = gameSettings.minPercentOfRightAnswers
    }

    private fun generateQuestion() {
        _question.value = generateQuestionUseCase(gameSettings.maxSumValue)
    }
    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }
}