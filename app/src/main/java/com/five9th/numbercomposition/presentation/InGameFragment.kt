package com.five9th.numbercomposition.presentation

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import com.five9th.numbercomposition.R
import com.five9th.numbercomposition.databinding.FragmentInGameBinding
import com.five9th.numbercomposition.domain.entities.GameResult
import com.five9th.numbercomposition.domain.entities.GameSettings
import com.five9th.numbercomposition.domain.entities.Level

class InGameFragment : BaseFragment<FragmentInGameBinding>(
    FragmentInGameBinding::inflate
) {
    private lateinit var level: Level

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
        Log.d("InGameFragment", "onCreate, level: $level")
    }

    private fun parseArgs() {
        level = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.TIRAMISU) {
            requireArguments().getParcelable(KEY_LEVEL, Level::class.java)
        } else {
            requireArguments().getParcelable(KEY_LEVEL) as Level?
        } ?: throw RuntimeException("Level is null")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvSum.setOnClickListener { //// temporary for testing
            launchGameResultFragment(
                GameResult(true, 100, 80,
                    GameSettings(10, 20, 3, 4)
                )
            )
        }
    }

    private fun launchGameResultFragment(gameResult: GameResult) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, GameResultFragment.newInstance(gameResult))
            .addToBackStack(null)
            .commit()
    }

    companion object {
        const val NAME = "InGameFragment"

        private const val KEY_LEVEL = "level"

        fun newInstance(level: Level): InGameFragment {
            return InGameFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_LEVEL, level)
                }
            }
        }
    }
}