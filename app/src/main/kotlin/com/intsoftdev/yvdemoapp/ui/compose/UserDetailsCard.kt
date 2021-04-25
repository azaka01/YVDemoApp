package com.intsoftdev.yvdemoapp.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.intsoftdev.domain.UserDetails
import com.intsoftdev.yvdemoapp.R

@Composable
fun UserDetailsCard(
    userDetails: UserDetails?,
    onClick: () -> Unit,
) {
    requireNotNull(userDetails)
    Column(verticalArrangement = Arrangement.Top) {
        Card(
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .padding(all = 16.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable(onClick = onClick),
            elevation = 8.dp,
        ) {
            Row(verticalAlignment = Alignment.Top) {
                Image(
                    painter = rememberCoilPainter(
                        request = userDetails.picUrl,
                        previewPlaceholder = R.drawable.ic_launcher,
                        shouldRefetchOnSizeChange = { _, _ -> false },
                    ),
                    contentDescription = "",
                    modifier = Modifier
                        .width(120.dp)
                        .height(120.dp)
                        .padding(all = 16.dp),
                    contentScale = ContentScale.Crop,
                )
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly, modifier = Modifier
                        .padding(all = 16.dp)
                ) {
                    Text(userDetails.displayName)
                    Text(userDetails.email)
                    Text(userDetails.displayAddress)
                }
            }
        }
    }
}


@Preview
@Composable
fun UserDetailsCardPreview() {
    UserDetailsCard(
        UserDetails(
            displayName = "Chief Monjubi",
            displayAddress = "Central Africa",
            email = "chief@mail.com",
            picUrl = "https://randomuser.me/api/portraits/thumb/men/59.jpg",
            latitude = 0.0,
            longitude = 0.0
        ),
        {}
    )
}