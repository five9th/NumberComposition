package com.five9th.numbercomposition.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameResult(
    val isWinner: Boolean,
    val totalAnswers: Int,
    val rightAnswers: Int,
    val gameSettings: GameSettings
) : Parcelable
{
    val rightAnswerPercent: Int
        get() = (rightAnswers * 100.0 / totalAnswers).toInt()

    val rightAnswerPercentStr: String  // temp
        get() = rightAnswerPercent.toString()

    val rightAnswersStr: String  // temp
        get() = rightAnswers.toString()
}
