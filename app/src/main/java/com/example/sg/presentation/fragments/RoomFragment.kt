package com.example.sg.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sg.data.database.DemonRoomDatabase
import com.example.sg.data.repository.DemonRepositoryImpl
import com.example.sg.databinding.FragmentRoomBinding
import com.example.sg.presentation.DemonViewModelFactory
import com.example.sg.presentation.viewmodels.RoomViewModel

class RoomFragment : Fragment() {
    private var _binding: FragmentRoomBinding? = null
    private val binding get() = _binding!!

    private val repository: DemonRepositoryImpl by lazy {
        DemonRepositoryImpl(DemonRoomDatabase.getDatabase(requireContext()).dao)
    }
    private val viewModel: RoomViewModel by lazy {
        ViewModelProvider(this, DemonViewModelFactory(repository))[RoomViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRoomBinding.inflate(inflater, container, false)

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

