package com.five9th.numbercomposition.domain.repository

import com.five9th.numbercomposition.domain.entities.GameSettings
import com.five9th.numbercomposition.domain.entities.Level
import com.five9th.numbercomposition.domain.entities.Question

interface GameRepository {
    fun generateQuestion(maxSum: Int, countOfOptions: Int): Question

    fun getGameSettings(level: Level): GameSettings
}