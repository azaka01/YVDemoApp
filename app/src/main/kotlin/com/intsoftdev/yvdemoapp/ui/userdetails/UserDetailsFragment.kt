package com.intsoftdev.yvdemoapp.ui.userdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.intsoftdev.yvdemoapp.ui.compose.UserDetailsCard
import com.intsoftdev.yvdemoapp.viewmodel.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class UserDetailsFragment : Fragment() {

    private val usersViewModel: UsersViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                Scaffold(topBar = {
                    TopAppBar(title = { Text(text = "${usersViewModel.selectedUser?.displayName}") })
                }) {
                    UserDetailsCard(userDetails = usersViewModel.selectedUser, onClick = {})
                }
            }
        }
    }
}