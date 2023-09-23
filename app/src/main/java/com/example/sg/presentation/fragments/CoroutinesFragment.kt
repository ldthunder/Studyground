package com.example.sg.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.sg.databinding.FragmentCoroutinesBinding
import com.example.sg.presentation.viewmodels.CoroutinesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

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
        setupObservers()
        setupClickListeners()
        return binding.root
    }

    private fun setupObservers(){
        viewModel.state.observe(viewLifecycleOwner){
            binding.tvCoroutinesData.text = it
        }
    }

    private fun setupClickListeners() {

        binding.btnStartCoroutine.setOnClickListener {
            val coroutine: Job = lifecycleScope.launch {
                viewModel.startCoroutine()
            }
            binding.btnStopCoroutine.setOnClickListener {
                coroutine.cancel()
            }
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}