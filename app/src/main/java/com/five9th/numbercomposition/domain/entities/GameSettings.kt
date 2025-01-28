package com.five9th.numbercomposition.domain.entities

data class GameSettings(
    val gameTimeInSeconds: Int,
    val maxSumValue: Int,
    val minRightAnswersCount: Int,
    val minRightPercentCount: Int
)
