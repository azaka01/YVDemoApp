package com.intsoftdev.yvdemoapp.ui.usersummary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.accompanist.coil.CoilImage
import com.intsoftdev.domain.UserDetails
import com.intsoftdev.yvdemoapp.R
import com.intsoftdev.yvdemoapp.viewmodel.ResultState
import com.intsoftdev.yvdemoapp.viewmodel.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class UsersSummaryFragment : Fragment() {

    // pull to refresh
    private val usersViewModel: UsersViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                Scaffold(topBar = {
                    TopAppBar(title = { Text(text = "All Users") })
                }) {
                    val resultState = usersViewModel.getLiveData().observeAsState()
                    WithResultState(resultState = resultState.value)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        usersViewModel.getAllUsers()
    }

    @Composable
    fun WithResultState(
        resultState: ResultState<List<UserDetails>>?,
        errorMessage: String = "Unable to download data"
    ) {
        when (resultState) {
            is ResultState.Busy -> {
                LoadingView()
            }
            is ResultState.Failure -> {
                Toast.makeText(
                    requireContext(),
                    errorMessage,
                    Toast.LENGTH_SHORT
                ).show()
            }
            is ResultState.Success -> {
                UpdateContent(resultState.data)
            }
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun UpdateContent(users: List<UserDetails>) {
        LazyVerticalGrid(
            cells = GridCells.Fixed(3)
        ) {
            items(users.size) { index ->
                UserSummaryCard(users[index]) {
                    usersViewModel.selectedUser = users[index]
                    findNavController().navigate(
                        UsersSummaryFragmentDirections.actionUserslistToUserdetails()
                    )
                }
            }
        }
    }
}

@Composable
fun UserSummaryCard(
    userDetails: UserDetails?,
    onClick: () -> Unit,
) {
    requireNotNull(userDetails)
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .padding(all = 16.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = 8.dp,
    ) {
        Column(
            modifier = Modifier
                .padding(all = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CoilImage(
                data = userDetails.picUrl,
                previewPlaceholder = R.drawable.ic_launcher,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                contentScale = ContentScale.Crop,
            )
            Text(
                userDetails.displayName,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.height(30.dp),
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun LoadingView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally))
    }
}
