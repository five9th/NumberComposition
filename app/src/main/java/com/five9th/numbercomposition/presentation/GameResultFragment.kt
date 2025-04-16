package com.five9th.numbercomposition.presentation

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.five9th.numbercomposition.R
import com.five9th.numbercomposition.databinding.FragmentGameResultBinding


class GameResultFragment : BaseFragment<FragmentGameResultBinding>(
    FragmentGameResultBinding::inflate
) {
    private val args by navArgs<GameResultFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        parseGameResult()

        setListeners()
    }

    private fun parseGameResult() {
        binding.gameResult = args.gameResult
    }

    private fun setText() {  // todo: delete this


        val requiredCount = args.gameResult.gameSettings.minRightAnswersCount
        val rightCount = args.gameResult.rightAnswers
        val rightCountColor = getColor(rightCount >= requiredCount)

        val requiredPercent = args.gameResult.gameSettings.minRightPercent
        val rightPercent = args.gameResult.rightAnswerPercent
        val rightPercentColor = getColor(rightPercent >= requiredPercent)

        binding.tvScoreAnswers.also {
            it.text = String.format(
                resources.getString(R.string.score_answers),
                rightCount.toString()
            )
            it.setTextColor(rightCountColor)
        }

        binding.tvRequiredAnswers.text = String.format(
            resources.getString(R.string.required_answers),
            requiredCount.toString()
        )

        binding.tvScorePercentage.also {
            it.text = String.format(
                resources.getString(R.string.score_percentage),
                rightPercent.toString()
            )
            it.setTextColor(rightPercentColor)
        }

        binding.tvRequiredPercentage.text = String.format(
            resources.getString(R.string.required_percentage),
            requiredPercent.toString()
        )
    }

    private fun getColor(isSuccess: Boolean): Int { // TODO: move to data binding
        return if (isSuccess) {
            ContextCompat.getColor(requireContext(), R.color.green)
        } else {
            ContextCompat.getColor(requireContext(), R.color.red)
        }
    }

    private fun setListeners() {
        binding.retryBtn.setOnClickListener {
            retryGame()
        }
    }

    private fun retryGame() {
        findNavController().popBackStack()
    }
}