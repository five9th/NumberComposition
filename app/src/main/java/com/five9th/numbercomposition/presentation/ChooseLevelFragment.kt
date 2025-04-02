package com.five9th.numbercomposition.presentation

import android.os.Bundle
import android.view.View
import com.five9th.numbercomposition.R
import com.five9th.numbercomposition.databinding.FragmentChooseLevelBinding
import com.five9th.numbercomposition.domain.entities.Level

class ChooseLevelFragment : BaseFragment<FragmentChooseLevelBinding>(
    FragmentChooseLevelBinding::inflate
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    private fun setListeners() {
        binding.buttonLevelTest.setOnClickListener {
            launchInGameFragment(Level.TEST)
        }
        binding.buttonLevelEasy.setOnClickListener {
            launchInGameFragment(Level.EASY)
        }
        binding.buttonLevelNormal.setOnClickListener {
            launchInGameFragment(Level.NORMAL)
        }
        binding.buttonLevelHard.setOnClickListener {
            launchInGameFragment(Level.HARD)
        }
    }

    private fun launchInGameFragment(level: Level) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, InGameFragment.newInstance(level))
            .addToBackStack(InGameFragment.NAME)
            .commit()
    }

    companion object {
        fun newInstance(): ChooseLevelFragment {
            return ChooseLevelFragment()
        }
    }
}