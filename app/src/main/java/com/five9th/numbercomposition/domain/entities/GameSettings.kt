package com.five9th.numbercomposition.domain.entities

import java.io.Serializable

data class GameSettings(
    val gameTimeInSeconds: Int,
    val maxSumValue: Int,
    val minRightAnswersCount: Int,
    val minRightPercentCount: Int
): Serializable
