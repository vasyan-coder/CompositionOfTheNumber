package com.trpp.compositionofthenumber.domain.entity

class GameResult(
    val winner: Boolean,
    val countOfRightAnswers: Int,
    val countOfQuestions: Int,
    val gameSettings: GameSettings
)