package com.five9th.numbercomposition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding>(
    // Passing a parameter via constructor here is pretty safe because only the successors of this
    // class will be used and they will have an empty constructor which will be called to recreate
    // the fragment and in which the BaseFragment's constructor will be called with hardcoded
    // [inflate] parameter
    private val inflate: (LayoutInflater, ViewGroup?, Boolean) -> VB,
) : Fragment() {

    private var _binding: VB? = null
    protected val binding: VB
        get() {
            return _binding ?: throw IllegalStateException("ViewBinding is null")
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}