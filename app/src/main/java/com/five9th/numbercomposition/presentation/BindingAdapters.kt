package com.five9th.numbercomposition.presentation

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.five9th.numbercomposition.R
import com.five9th.numbercomposition.domain.entities.GameResult

@BindingAdapter("requiredAnswers")
fun bindRequiredAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_answers),
        count.toString()
    )
}

@BindingAdapter("scoreAnswers")
fun bindScoreAnswers(textView: TextView, gameResult: GameResult) {
    val requiredCount = gameResult.gameSettings.minRightAnswersCount
    val rightCount = gameResult.rightAnswers
    val rightCountColor = getResultColor(
        textView.context,
        rightCount >= requiredCount)

    textView.text = String.format(
        textView.context.getString(R.string.score_answers),
        rightCount.toString()
    )

    textView.setTextColor(rightCountColor)
}

@BindingAdapter("requiredPercentage")
fun bindRequiredPercentage(textView: TextView, percent: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_percentage),
        percent.toString()
    )
}

@BindingAdapter("scorePercentage")
fun bindScorePercentage(textView: TextView, gameResult: GameResult) {
    val requiredPercent = gameResult.gameSettings.minRightPercent
    val rightPercent = gameResult.rightAnswerPercent
    val rightPercentColor = getResultColor(
        textView.context,
        rightPercent >= requiredPercent)

    textView.text = String.format(
        textView.context.getString(R.string.score_percentage),
        rightPercent.toString()
    )

    textView.setTextColor(rightPercentColor)
}

@BindingAdapter("emojiResult")
fun bindEmojiResult(imageView: ImageView, isWinner: Boolean) {
    val imgId = if (isWinner) R.drawable.happy_emoji else R.drawable.sad_emoji
    imageView.setImageResource(imgId)
}

private fun getResultColor(context: Context, isSuccess: Boolean): Int {
    return if (isSuccess) {
        context.getColor(R.color.green)
    } else {
        context.getColor(R.color.red)
    }
}