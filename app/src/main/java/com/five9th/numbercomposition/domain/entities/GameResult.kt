package com.five9th.numbercomposition.domain.entities

import java.io.Serializable

data class GameResult(
    val isWinner: Boolean,
    val totalAnswers: Int,
    val rightAnswers: Int,
    val gameSettings: GameSettings
) : Serializable
