package com.trpp.compositionofthenumber.domain.repository

import com.trpp.compositionofthenumber.domain.entity.GameSettings
import com.trpp.compositionofthenumber.domain.entity.Level
import com.trpp.compositionofthenumber.domain.entity.Question
import com.trpp.compositionofthenumber.domain.entity.Type

interface GameRepository {

    fun generateQuestion(
        maxSumValue: Int,
        countOfOptions: Int
    ): Question

    fun getGameSettings(level: Level, type: Type): GameSettings

    fun generateSubQuestion(
        maxSumValue: Int,
        countOfOptions: Int
    ): Question

    fun generateMulQuestion(
        maxSumValue: Int,
        countOfOptions: Int
    ): Question
}