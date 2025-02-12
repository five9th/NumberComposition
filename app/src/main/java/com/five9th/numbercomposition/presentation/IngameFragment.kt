package com.five9th.numbercomposition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.five9th.numbercomposition.databinding.FragmentInGameBinding

class InGameFragment : BaseFragment<FragmentInGameBinding>() {
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentInGameBinding {
        return FragmentInGameBinding.inflate(inflater, container, false)
    }


}