package com.example.sg.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.sg.databinding.FragmentOkhttpBinding
import com.example.sg.presentation.viewmodels.OkhttpViewModel

class OkhttpFragment : Fragment() {
    private var _binding: FragmentOkhttpBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<OkhttpViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOkhttpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = OkhttpFragment()
    }
}