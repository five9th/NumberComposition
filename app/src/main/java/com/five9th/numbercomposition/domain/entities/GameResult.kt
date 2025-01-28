package com.five9th.numbercomposition.domain.entities

data class GameResult(
    val isWinner: Boolean,
    val totalAnswers: Int,
    val rightAnswers: Int,
    val gameSettings: GameSettings
)
