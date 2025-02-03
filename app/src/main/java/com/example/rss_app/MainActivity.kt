package com.example.rss_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rss_app.data.models.RssResponse
import com.example.rss_app.ui.theme.RssappTheme
import com.example.rss_app.ui.viewmodels.RssFeedState
import com.example.rss_app.ui.viewmodels.RssViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            RssappTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RSSFeedScreen(innerPadding)
                }
            }
        }
    }
}


@Composable
fun RSSFeedScreen(
    paddingValues: PaddingValues,
    viewModel: RssViewModel = hiltViewModel()
) {

    val rssFeedState by viewModel.rssFeeds.collectAsState()

    when (val state = rssFeedState) {
        is RssFeedState.Loading -> Loading()
        is RssFeedState.Error -> Error()
        is RssFeedState.Success -> Successful(state.data)
    }

}

@Composable
fun Loading() {
    CircularProgressIndicator(
        modifier = Modifier.width(64.dp),
        color = MaterialTheme.colorScheme.secondary,
        trackColor = MaterialTheme.colorScheme.surfaceVariant,
    )
}

@Composable
fun Error() {
    Text(
        text = "shit bruv"
    )
}

@Composable
fun Successful(state: RssResponse) {
    Column {
        Text(
            text = state.channel?.title.toString()
        )
        Text(
            text = state.channel?.description.toString()
        )
    }
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RssappTheme {
        Greeting("Android")
    }
}