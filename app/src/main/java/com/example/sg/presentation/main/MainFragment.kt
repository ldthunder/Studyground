package com.example.sg.presentation.main

import android.app.FragmentManager.FragmentLifecycleCallbacks
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.sg.R
import com.example.sg.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }

    private fun navigateTo(id: Int){
        findNavController().navigate(id)
    }

    private fun setupClickListeners(){
        binding.buttonCoroutines.setOnClickListener {
            navigateTo(R.id.action_mainFragment_to_coroutinesFragment)
        }

        binding.buttonNetwork.setOnClickListener {
            navigateTo(R.id.action_mainFragment_to_networkFragment)
        }

        binding.buttonWorkers.setOnClickListener {
            navigateTo(R.id.action_mainFragment_to_workersFragment)
        }

        binding.buttonListAdapter.setOnClickListener {
            navigateTo(R.id.action_mainFragment_to_listFragment)
        }

        binding.buttonRoom.setOnClickListener {
            navigateTo(R.id.action_mainFragment_to_roomFragment)
        }

        binding.buttonFirebase.setOnClickListener {
            navigateTo(R.id.action_mainFragment_to_firebaseFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}