package com.five9th.numbercomposition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.five9th.numbercomposition.databinding.FragmentChooseLevelBinding

class ChooseLevelFragment : BaseFragment<FragmentChooseLevelBinding>() {
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentChooseLevelBinding {
        return FragmentChooseLevelBinding.inflate(inflater, container, false)
    }


}