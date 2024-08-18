package com.example.secretagents.presentation.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.secretagents.R
import com.example.secretagents.data.model.users.DataX
import com.example.secretagents.presentation.viewmodel.MainActivityViewModel
import com.example.secretagents.ui.theme.SurfaceContainerHigh
import com.example.secretagents.ui.theme.SurfaceLight
import com.example.secretagents.ui.theme.TextColorLow
import kotlinx.coroutines.delay
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun MainScreen(navController: NavController, viewModel: MainActivityViewModel = hiltViewModel()) {

    LaunchedEffect(true) {
        delay(1000)
        viewModel.getResponseData()
    }

    val users = viewModel.responseData.observeAsState().value
    val loadingState = viewModel.loading.observeAsState().value
    val searchQuery = remember { mutableStateOf("") }

    val filteredItems = users?.data?.data?.filter {
        it.name.first.contains(searchQuery.value, ignoreCase = true) || it.name.last.contains(
            searchQuery.value, ignoreCase = true
        )
    }

    if (users != null && loadingState == false) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(SurfaceContainerHigh),
        ) {
            SearchBar(searchQuery = searchQuery.value) { searchQuery.value = it }
            LazyColumn(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(
                    top = 16.dp, bottom = 16.dp
                )
            ) {
                items(filteredItems!!) { user ->
                    CardViewItem(user, navController)
                }
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Dialog(
                onDismissRequest = {}, properties = DialogProperties(usePlatformDefaultWidth = true)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            }
        }
    }
}

@Composable
fun CardViewItem(user: DataX, navController: NavController) {
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .height(94.dp)
            // .shadow(0.5.dp, RoundedCornerShape(12.dp))
            .background(SurfaceLight, RoundedCornerShape(12.dp))
            .clickable {
                val encodedUrl =
                    URLEncoder.encode(user.picture.medium, StandardCharsets.UTF_8.toString())
                navController.navigate("user_details/${user.name.title + " " + user.name.first + " " + user.name.last}/${user.email}/${encodedUrl}/${user.phone}/${user.dob.date}/${user.dob.age.toString()}/${user.gender}/${user.location.street.number.toString() + ", " + user.location.street.name + ", " + user.location.city + ", " + user.location.country + ", " + user.location.state + ", " + user.location.postcode}/${user.location.coordinates.latitude + user.location.coordinates.longitude}/${user.nat}")
            }, contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row {
                Image(
                    painter = rememberAsyncImagePainter(user.picture.medium),
                    contentDescription = null,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                )
                Column(modifier = Modifier.padding(start = 16.dp, top = 2.dp, end = 16.dp)) {
                    Text(
                        text = "${user.name.title} ${user.name.first} ${user.name.last}",
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = user.email,
                        color = TextColorLow,
                        modifier = Modifier.padding(top = 3.dp),
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.lato_regular)),
                        fontWeight = FontWeight.Light,
                    )
                }
                Spacer(Modifier.weight(1f))
                CircleImageView()
            }
        }
    }
}

@Preview
@Composable
fun CircleImageView() {
    Image(
        painter = painterResource(R.drawable.bookmark),
        contentDescription = "Circle Image",
        contentScale = ContentScale.Inside,
        modifier = Modifier
            .background(Color.White, shape = CircleShape)
            .size(28.dp)
    )
}

