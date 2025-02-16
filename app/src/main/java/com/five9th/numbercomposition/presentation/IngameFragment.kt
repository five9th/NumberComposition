package com.five9th.numbercomposition.presentation

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import com.five9th.numbercomposition.databinding.FragmentInGameBinding
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
            requireArguments().getSerializable(KEY_LEVEL, Level::class.java)
        } else {
            requireArguments().getSerializable(KEY_LEVEL) as? Level
        } ?: throw RuntimeException("Level is null")
    }

    companion object {
        private const val KEY_LEVEL = "level"

        fun newInstance(level: Level): InGameFragment {
            return InGameFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(KEY_LEVEL, level)
                }
            }
        }
    }
}