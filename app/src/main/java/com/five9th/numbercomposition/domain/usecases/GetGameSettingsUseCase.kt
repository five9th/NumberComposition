package com.five9th.numbercomposition.domain.usecases

import com.five9th.numbercomposition.domain.entities.GameSettings
import com.five9th.numbercomposition.domain.entities.Level
import com.five9th.numbercomposition.domain.repository.GameRepository

class GetGameSettingsUseCase(
    private val repository: GameRepository
) {
    operator fun invoke(level: Level): GameSettings {
        return repository.getGameSettings(level)
    }
}