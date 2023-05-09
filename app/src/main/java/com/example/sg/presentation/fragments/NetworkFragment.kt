package com.example.sg.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.sg.databinding.FragmentNetworkBinding
import com.example.sg.presentation.viewmodels.NetworkViewModel

class NetworkFragment : Fragment() {
    private var _binding: FragmentNetworkBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<NetworkViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNetworkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = NetworkFragment()
    }
}