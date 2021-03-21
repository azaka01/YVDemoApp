package com.intsoftdev.yvdemoapp.ui.usersummary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.intsoftdev.domain.UserDetails
import com.intsoftdev.yvdemoapp.viewmodel.ResultState
import com.intsoftdev.yvdemoapp.viewmodel.UsersViewModel
import com.intsoftdev.yvdemoapp.databinding.FragmentUsersSummaryListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersSummaryFragment : Fragment(), (UserDetails) -> Unit {

    private val usersViewModel: UsersViewModel by activityViewModels()

    private var _binding: FragmentUsersSummaryListBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersSummaryListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.swipeRefreshLayout.setOnRefreshListener {
            usersViewModel.getAllUsers()
        }

        usersViewModel.getLiveData().observe(viewLifecycleOwner, { resultState ->
            binding.progress.isVisible = false
            binding.swipeRefreshLayout.isRefreshing = false
            when (resultState) {
                is ResultState.Success -> bindUsers(resultState.data)
                is ResultState.Busy -> binding.progress.isVisible = true
                is ResultState.Failure -> {
                    Toast.makeText(
                        requireContext(),
                        "Unable to download data",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })

        usersViewModel.getAllUsers()
    }

    private fun bindUsers(users: List<UserDetails>) {
        binding.recyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.recyclerView.adapter = UsersSummaryAdapter(users, this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun invoke(selectedUser: UserDetails) {
        usersViewModel.selectedUser = selectedUser
        findNavController().navigate(
            UsersSummaryFragmentDirections.actionUserslistToUserdetails()
        )
    }
}