package com.example.sg.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.sg.databinding.FragmentCoroutinesBinding
import com.example.sg.presentation.viewmodels.CoroutinesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoroutinesFragment : Fragment() {
    private var _binding: FragmentCoroutinesBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<CoroutinesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoroutinesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = CoroutinesFragment()
    }


}