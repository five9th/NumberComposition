package com.five9th.numbercomposition.presentation

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.five9th.numbercomposition.R
import com.five9th.numbercomposition.databinding.FragmentGameResultBinding
import com.five9th.numbercomposition.domain.entities.GameResult


class GameResultFragment : BaseFragment<FragmentGameResultBinding>(
    FragmentGameResultBinding::inflate
) {
    private lateinit var gameResult: GameResult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
        Log.d("GameResultFragment", "onCreate, gameResult: $gameResult")
    }

    private fun parseArgs() {
        gameResult = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.TIRAMISU) {
            requireArguments().getParcelable(KEY_GAME_RESULT, GameResult::class.java)
        } else {
            requireArguments().getParcelable(KEY_GAME_RESULT) as? GameResult
        } ?: throw RuntimeException("GameResult is null")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        parseGameResult()

        setListeners()
    }

    private fun parseGameResult() {
        setImage()
        setText()
    }

    private fun setImage() {
        val imgId = if (gameResult.isWinner) R.drawable.happy_emoji else R.drawable.sad_emoji
        binding.emojiResult.setImageResource(imgId)
    }

    private fun setText() {
        val requiredCount = gameResult.gameSettings.minRightAnswersCount
        val rightCount = gameResult.rightAnswers
        val rightCountColor = getColor(rightCount >= requiredCount)

        val requiredPercent = gameResult.gameSettings.minRightPercent
        val rightPercent = gameResult.rightAnswerPercent
        val rightPercentColor = getColor(rightPercent >= requiredPercent)

        binding.tvScoreAnswers.also {
            it.text = String.format(
                resources.getString(R.string.score_answers),
                rightCount.toString()
            )
            it.setTextColor(rightCountColor)
        }

        binding.tvRequiredAnswers.text = String.format(
            resources.getString(R.string.required_score),
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

    private fun getColor(isSuccess: Boolean): Int {
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

    companion object {

        private const val KEY_GAME_RESULT = "game_result"

        fun bundleForNewInstance(gameResult: GameResult): Bundle {
            return Bundle().apply {
                putParcelable(KEY_GAME_RESULT, gameResult)
            }
        }

        @JvmStatic
        fun newInstance(gameResult: GameResult) =
            GameResultFragment().apply {
                arguments = bundleForNewInstance(gameResult)
            }
    }
}