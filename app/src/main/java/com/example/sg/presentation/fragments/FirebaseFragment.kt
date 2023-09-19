package com.example.sg.presentation.fragments

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.sg.R
import com.example.sg.databinding.FragmentFirebaseBinding
import com.example.sg.presentation.google_auth.GoogleAuthClient
import com.example.sg.presentation.google_auth.UserData
import com.example.sg.presentation.viewmodels.FirebaseViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FirebaseFragment @Inject constructor() : Fragment() {
    @Inject
    lateinit var googleAuthClient: GoogleAuthClient
    private var _binding: FragmentFirebaseBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<FirebaseViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirebaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUi()
        val launcher = createForActivityResultLauncher()
        setUpSignInClickListeners(launcher)
        setUpObservers()
    }

    @SuppressLint("SetTextI18n")
    private fun updateUi() {
        val currentUser: UserData? = googleAuthClient.getSignedInUser()
        if (currentUser != null) {
            binding.tvFirebaseData.text = "Name: ${currentUser.username}" +
                    " UID: ${currentUser.userId}"
            Glide.with(requireContext())
                .load(currentUser.profilePictureUrl)
                .placeholder(R.drawable.elinaicolast_background)
                .into(binding.imgFirebase)
            lifecycleScope.launch {
                val radja = viewModel.getFirebaseUserByUid(currentUser)!!.data?.get("name")
                println("Yuer $radja")
            }
        } else {
            binding.tvFirebaseData.text = ""
            binding.imgFirebase.setImageResource(R.drawable.elinaicolast_background)
        }
    }

    private fun createForActivityResultLauncher(): ActivityResultLauncher<IntentSenderRequest> {
        val contract = ActivityResultContracts.StartIntentSenderForResult()
        val launcher = registerForActivityResult(contract) { result ->
            if (result.resultCode == RESULT_OK) {
                lifecycleScope.launch {
                    val signInResult = googleAuthClient.signInWithIntent(
                        intent = result.data ?: return@launch
                    )
                    viewModel.onSignInResult(signInResult)
                    if (signInResult.data != null){
                        viewModel.addUserToFirestore(signInResult.data)
                    }
                    updateUi()
                }
            }
        }
        return launcher
    }

    private fun setUpSignInClickListeners(launcher: ActivityResultLauncher<IntentSenderRequest>) {
        binding.googleSign.setOnClickListener {
            lifecycleScope.launch {
                val signInIntentSender = googleAuthClient.signIn()
                launcher.launch(
                    IntentSenderRequest.Builder(
                        signInIntentSender ?: return@launch
                    ).build()
                )
            }
        }

        binding.btnSignOut.setOnClickListener {
            lifecycleScope.launch {
                googleAuthClient.signOut()
                updateUi()
            }
        }
    }

    private fun setUpObservers() {
        viewModel.state.observe(viewLifecycleOwner) {
            println("YUER, ${it.isSignInSuccessful} ${it.signInError}")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}