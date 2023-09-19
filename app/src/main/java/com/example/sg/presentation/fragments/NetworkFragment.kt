package com.example.sg.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.sg.databinding.FragmentNetworkBinding
import com.example.sg.presentation.viewmodels.NetworkViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NetworkFragment : Fragment() {
    private var _binding: FragmentNetworkBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NetworkViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNetworkBinding.inflate(inflater, container, false)
        setupClickListeners()
        return binding.root
    }

    private fun setupClickListeners() {
        binding.btnDoQuery.setOnClickListener {
            lifecycleScope.launch {
                binding.tvNetworkData.text = viewModel.fetchTodo().toString()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}