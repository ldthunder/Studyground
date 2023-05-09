package com.example.sg.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.sg.databinding.FragmentKtorBinding
import com.example.sg.presentation.viewmodels.KtorViewModel

class KtorFragment : Fragment() {
    private var _binding: FragmentKtorBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<KtorViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentKtorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = KtorFragment()
    }
}