package com.ioasys.diversidade.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ioasys.diversidade.databinding.FragmentSuccessAuthBinding
import com.ioasys.diversidade.utils.NetworkResult
import com.ioasys.diversidade.viewmodels.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SuccessAuthFragment: Fragment() {

    private var _binding: FragmentSuccessAuthBinding? = null
    private val binding get() = _binding!!

    private lateinit var authViewModel: AuthViewModel
    private val args: SuccessAuthFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authViewModel = ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSuccessAuthBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        binding.successAccess.setOnClickListener {
            authViewModel.signIn(args.email, args.password)
        }

        authViewModel.userData.observe(viewLifecycleOwner, {res ->
            when(res) {
                is NetworkResult.Success -> {
                    val id = res.data?.user?.id
                    val name = res.data?.user?.firstName
                    val token = res.data?.token

                    authViewModel.saveUserInfo(id!!, name!!, token!!)

                    val action = SuccessAuthFragmentDirections.actionSuccessAuthFragmentToMyNav(id, name, token)
                    findNavController().navigate(action)
                    authViewModel.userData.postValue(null)
                }
            }
        })

        return binding.root
    }

    override fun onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu()
        _binding = null
    }
}