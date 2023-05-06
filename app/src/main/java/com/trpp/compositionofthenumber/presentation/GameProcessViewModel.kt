package com.trpp.compositionofthenumber.presentation

import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.compositionofthenumber.R
import com.trpp.compositionofthenumber.data.GameRepositoryImpl
import com.trpp.compositionofthenumber.domain.entity.GameResult
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
    private var countOfRightAnswers = 0
    private var countOfQuestions = 0
    private val _percentOfRightAnswers = MutableLiveData<Int>()
    private val _progressAnswers = MutableLiveData<String>()
    private val _enoughCountOfRightAnswers = MutableLiveData<Boolean>()
    private val _enoughPercentOfRightAnswers = MutableLiveData<Boolean>()
    private val _gameResult = MutableLiveData<GameResult>()
    val gameResult: LiveData<GameResult>
        get() = _gameResult
    val enoughPercentOfRightAnswers: LiveData<Boolean>
        get() = _enoughCountOfRightAnswers
    val enoughCountOfRightAnswers: LiveData<Boolean>
        get() = _enoughCountOfRightAnswers
    val progressAnswers: LiveData<String>
        get() = _progressAnswers
    val percentOfRightAnswers: LiveData<Int>
        get() = _percentOfRightAnswers
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
        startGame()
    }

    private fun startGame() {
        getGameSettings()
        startTimer()
        generateQuestion()
        updateProgress()
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
                finishGame()
            }
        }
        timer?.start()
    }

    private fun finishGame() {
        _gameResult.value = GameResult(
            winner = enoughCountOfRightAnswers.value == true
                    && enoughPercentOfRightAnswers.value == true,
            countOfRightAnswers = countOfRightAnswers,
            countOfQuestions = countOfQuestions,
            gameSettings = gameSettings
        )
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

    private fun calcPercentOfRightAnswers(): Int {
        if (countOfQuestions == 0)
            return 0
        return ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
    }

    private fun updateProgress() {
        val percent = calcPercentOfRightAnswers()
        _percentOfRightAnswers.value = percent
        _progressAnswers.value = String.format(
            context.resources.getString(R.string.progress_answers),
            countOfRightAnswers,
            gameSettings.minCountOfRightAnswers
        )
        _enoughCountOfRightAnswers.value =
            countOfRightAnswers >= gameSettings.minCountOfRightAnswers
        _enoughPercentOfRightAnswers.value = percent >= gameSettings.minPercentOfRightAnswers
    }

    private fun checkAnswer(number: Int) {
        val rightAnswer = question.value?.rightAnswer
        if (number == rightAnswer) {
            countOfRightAnswers++
        }
        countOfQuestions++
    }

    fun chooseAnswer(number: Int) {
        checkAnswer(number)
        updateProgress()
        generateQuestion()
    }
}