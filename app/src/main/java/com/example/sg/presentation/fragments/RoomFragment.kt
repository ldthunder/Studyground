package com.example.sg.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sg.data.database.Demon
import com.example.sg.data.database.DemonRoomDatabase
import com.example.sg.data.repository.DemonRepositoryImpl
import com.example.sg.databinding.FragmentRoomBinding
import com.example.sg.presentation.DemonViewModelFactory
import com.example.sg.presentation.viewmodels.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoomFragment : Fragment() {
    private var _binding: FragmentRoomBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RoomViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRoomBinding.inflate(inflater, container, false)

        binding.btnDoChanges.setOnClickListener {
            viewModel.upsert(Demon(0, "baal", 33))
        }
        setupObservers()

        return binding.root
    }

    private fun setupObservers() {
        viewModel.demonsList.observe(viewLifecycleOwner, Observer { list ->
            binding.tvDataBaseInfo.text = list.joinToString()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

