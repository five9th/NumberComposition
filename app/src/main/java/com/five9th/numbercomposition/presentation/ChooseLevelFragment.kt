package com.five9th.numbercomposition.presentation

import android.os.Bundle
import android.view.View
import com.five9th.numbercomposition.databinding.FragmentChooseLevelBinding

class ChooseLevelFragment : BaseFragment<FragmentChooseLevelBinding>(
    FragmentChooseLevelBinding::inflate
) {
    companion object {
        fun newInstance(): ChooseLevelFragment {
            return ChooseLevelFragment()
        }
    }
}