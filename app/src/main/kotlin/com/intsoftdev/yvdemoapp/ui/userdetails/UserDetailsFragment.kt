package com.intsoftdev.yvdemoapp.ui.userdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import coil.load
import com.intsoftdev.yvdemoapp.databinding.FragmentUserDetailsBinding
import com.intsoftdev.yvdemoapp.viewmodel.UsersViewModel


class UserDetailsFragment : Fragment() {

    private val usersViewModel: UsersViewModel by activityViewModels()

    private var _binding: FragmentUserDetailsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.userName.text = usersViewModel.selectedUser?.displayName
        binding.userAddress.text = usersViewModel.selectedUser?.displayAddress
        binding.userImage.load(usersViewModel.selectedUser?.picUrl)
        binding.userEmail.text = usersViewModel.selectedUser?.email
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}