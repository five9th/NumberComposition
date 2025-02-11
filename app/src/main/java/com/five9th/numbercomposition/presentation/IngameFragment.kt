package com.five9th.numbercomposition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.five9th.numbercomposition.databinding.FragmentInGameBinding

class InGameFragment : Fragment() {

    private var _binding: FragmentInGameBinding? = null
    private val binding: FragmentInGameBinding
        get() = _binding ?: throw RuntimeException("FragmentInGameBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}