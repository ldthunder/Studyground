package com.example.sg.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.sg.databinding.FragmentWorkersBinding
import com.example.sg.presentation.viewmodels.WorkersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorkersFragment : Fragment() {
    private var _binding: FragmentWorkersBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<WorkersViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWorkersBinding.inflate(inflater, container, false)

        setupObservers()

        setupListeners()

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun setupObservers() {
        viewModel.state.observe(viewLifecycleOwner){ infoList ->
            if (infoList.isNullOrEmpty()){
                return@observe
            }
            val info = infoList[0]

            binding.btnStartWorker.isEnabled = info.state.isFinished

            binding.tvWorkerData.text = "Status: ${info.state}," +
                    " progress: ${info.progress}," +
                    " Attempt count: ${info.runAttemptCount}"

        }
    }

    private fun setupListeners() {
        binding.btnStartWorker.setOnClickListener {
            viewModel.startWorker()
        }

        binding.btnStopWorker.setOnClickListener {
            viewModel.cancelWorker()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}