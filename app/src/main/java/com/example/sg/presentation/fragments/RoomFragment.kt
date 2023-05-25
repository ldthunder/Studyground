package com.example.sg.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.sg.data.database.Demon
import com.example.sg.databinding.FragmentRoomBinding
import com.example.sg.presentation.viewmodels.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RoomFragment : Fragment() {
    /* View Binding */
    private var _binding: FragmentRoomBinding? = null
    private val binding get() = _binding!!
    /* ViewModel */
    private val viewModel: RoomViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRoomBinding.inflate(inflater, container, false)

        /* Temporary */
        binding.btnDoChanges.setOnClickListener {
            lifecycleScope.launch{
                binding.tvDataBaseInfo.text = viewModel.logTheNetwork().toString()
            }
        }
        /* Temporary */

        setupObservers()

        return binding.root
    }

    private fun setupObservers() {
        viewModel.demonsList.observe(viewLifecycleOwner) { list ->
            binding.tvDataBaseInfo.text = list.joinToString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

