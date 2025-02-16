package com.five9th.numbercomposition.presentation

import android.os.Bundle
import android.view.View
import com.five9th.numbercomposition.R
import com.five9th.numbercomposition.databinding.FragmentIntroductionBinding

class IntroductionFragment : BaseFragment<FragmentIntroductionBinding>(
    FragmentIntroductionBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.startBtn.setOnClickListener {
            launchChooseLevelFragment()
        }
    }

    private fun launchChooseLevelFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, ChooseLevelFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }

    companion object {
        fun newInstance(): IntroductionFragment {
            return IntroductionFragment()
        }
    }
}