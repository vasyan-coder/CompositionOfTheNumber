package com.trpp.compositionofthenumber.domain.usecases

import com.trpp.compositionofthenumber.domain.entity.Question
import com.trpp.compositionofthenumber.domain.repository.GameRepository

class GenerateDivQuestionUseCase(
    private val repository: GameRepository
) {

    operator fun invoke(maxSumValue: Int): Question {
        return repository.generateDivQuestion(maxSumValue, COUNT_OF_OPTIONS)
    }

    private companion object {

        private const val COUNT_OF_OPTIONS = 6
    }
}