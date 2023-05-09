package com.trpp.compositionofthenumber.data

import com.trpp.compositionofthenumber.domain.entity.GameSettings
import com.trpp.compositionofthenumber.domain.entity.Level
import com.trpp.compositionofthenumber.domain.entity.Question
import com.trpp.compositionofthenumber.domain.entity.Type
import com.trpp.compositionofthenumber.domain.repository.GameRepository
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

object GameRepositoryImpl : GameRepository {

    private const val MIN_SUM_VALUE = 2
    private const val MIN_ANSWER_VALUE = 1

    private const val MAX_COEF_VALUE = 6
    private const val MIN_COEF_VALUE = 3

    override fun generateQuestion(maxSumValue: Int, countOfOptions: Int): Question {
        val sum = Random.nextInt(MIN_SUM_VALUE, maxSumValue + 1)
        val visibleNumber = Random.nextInt(MIN_ANSWER_VALUE, sum)
        val options = HashSet<Int>()
        val rightAnswer = sum - visibleNumber
        options.add(rightAnswer)
        while (options.size < countOfOptions) {
            options.add(Random.nextInt(MIN_ANSWER_VALUE, maxSumValue))
        }
        return Question(sum, visibleNumber, options.toList())
    }

    override fun generateSubQuestion(maxSumValue: Int, countOfOptions: Int): Question {
        val sum = Random.nextInt(MIN_SUM_VALUE, maxSumValue + 1)
        val visibleNumber = Random.nextInt(MIN_ANSWER_VALUE, sum)
        val options = HashSet<Int>()
        val rightAnswer = sum + visibleNumber
        options.add(rightAnswer)
        while (options.size < countOfOptions) {
            options.add(Random.nextInt(MIN_ANSWER_VALUE, maxSumValue))
        }
        return Question(sum, visibleNumber, options.toList())
    }

    override fun generateMulQuestion(maxSumValue: Int, countOfOptions: Int): Question {
        val visibleNumber = Random.nextInt(MIN_ANSWER_VALUE, maxSumValue + 1)
        val sum = visibleNumber * Random.nextInt(MIN_SUM_VALUE, MAX_COEF_VALUE)
        val options = HashSet<Int>()
        val rightAnswer = sum / visibleNumber
        options.add(rightAnswer)
        while (options.size < countOfOptions) {
            options.add(Random.nextInt(MIN_ANSWER_VALUE, maxSumValue))
        }
        return Question(sum, visibleNumber, options.toList())
    }

    override fun generateDivQuestion(maxSumValue: Int, countOfOptions: Int): Question {
        val visibleNumber = Random.nextInt(MIN_ANSWER_VALUE, maxSumValue / 2)
        val sum = visibleNumber * Random.nextInt(MIN_SUM_VALUE, MIN_COEF_VALUE)
        val options = HashSet<Int>()
        val rightAnswer = sum * visibleNumber
        options.add(rightAnswer)
        while (options.size < countOfOptions) {
            options.add(Random.nextInt(1, maxSumValue))
        }
        // swap sum and visibleNumber
        return Question(visibleNumber, sum, options.toList())
    }

    override fun getGameSettings(level: Level, type: Type): GameSettings {
        return when (level) {
            Level.TEST -> {
                GameSettings(
                    10,
                    3,
                    50,
                    8,
                    Level.TEST,
                    type
                )
            }

            Level.EASY -> {
                GameSettings(
                    10,
                    10,
                    70,
                    60,
                    Level.EASY,
                    type
                )
            }

            Level.NORMAL -> {
                GameSettings(
                    20,
                    20,
                    80,
                    40,
                    Level.NORMAL,
                    type
                )
            }

            Level.HARD -> {
                GameSettings(
                    30,
                    30,
                    90,
                    40,
                    Level.HARD,
                    type
                )
            }
        }
    }
}