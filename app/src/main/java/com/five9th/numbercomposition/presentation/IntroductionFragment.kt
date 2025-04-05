package com.five9th.numbercomposition.presentation

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
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
        findNavController().navigate(R.id.action_introductionFragment_to_chooseLevelFragment)
    }
}