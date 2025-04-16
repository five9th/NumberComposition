package com.five9th.numbercomposition.presentation

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.five9th.numbercomposition.R

@BindingAdapter("requiredAnswers")
fun bindRequiredAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_answers),
        count.toString()
    )
}

@BindingAdapter("scoreAnswers")
fun bindScoreAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.score_answers),
        count.toString()
    )

    // TODO: textView.setTextColor()
}

@BindingAdapter("requiredPercentage")
fun bindRequiredPercentage(textView: TextView, percent: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_percentage),
        percent.toString()
    )
}

@BindingAdapter("scorePercentage")
fun bindScorePercentage(textView: TextView, percent: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.score_percentage),
        percent.toString()
    )

    // TODO: textView.setTextColor()
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