package com.five9th.numbercomposition.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameSettings(
    val gameTimeInSeconds: Int,
    val maxSumValue: Int,
    val minRightAnswersCount: Int,
    val minRightPercent: Int
): Parcelable {
    val minRightAnswersCountStr: String  // temp
        get() = minRightAnswersCount.toString()

    val minRightPercentStr: String  // temp
        get() = minRightPercent.toString()
}
