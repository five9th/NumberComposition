package com.five9th.numbercomposition.presentation

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
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

    private fun setListeners() {
        binding.retryBtn.setOnClickListener {
            retryGame()
        }
    }

    private fun retryGame() {
        findNavController().popBackStack()
    }
}