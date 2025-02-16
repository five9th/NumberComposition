package com.five9th.numbercomposition.presentation

import android.os.Build
import android.os.Bundle
import android.util.Log
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
            requireArguments().getSerializable(KEY_GAME_RESULT, GameResult::class.java)
        } else {
            requireArguments().getSerializable(KEY_GAME_RESULT) as? GameResult
        } ?: throw RuntimeException("GameResult is null")
    }

    companion object {

        private const val KEY_GAME_RESULT = "game_result"

        @JvmStatic
        fun newInstance(gameResult: GameResult) =
            GameResultFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(KEY_GAME_RESULT, gameResult)
                }
            }
    }
}