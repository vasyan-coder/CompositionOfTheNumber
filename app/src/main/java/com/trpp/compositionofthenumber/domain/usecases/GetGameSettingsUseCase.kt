package com.trpp.compositionofthenumber.domain.usecases

import com.trpp.compositionofthenumber.domain.entity.GameSettings
import com.trpp.compositionofthenumber.domain.entity.Level
import com.trpp.compositionofthenumber.domain.entity.Type
import com.trpp.compositionofthenumber.domain.repository.GameRepository

class GetGameSettingsUseCase(
    private val repository: GameRepository
) {

    operator fun invoke(level: Level, type: Type): GameSettings {
        return repository.getGameSettings(level, type)
    }
}