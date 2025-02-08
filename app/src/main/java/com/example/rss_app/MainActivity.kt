package com.example.rss_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rss_app.data.models.RssItem
import com.example.rss_app.ui.screens.HomeScreen
import com.example.rss_app.ui.theme.RssappTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()



        setContent {
            RssappTheme {
                Scaffold { innerPadding ->
                    HomeScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .consumeWindowInsets(innerPadding),

                        )
                }
            }
        }
    }
}


//@Composable
//fun RSSFeedScreen(
//    viewModel: RssViewModel = hiltViewModel(),
//    modifier: Modifier = Modifier
//) {
//
//    val rssFeedState by viewModel.rssFeeds.collectAsState()
//
//    when (val state = rssFeedState) {
//        is RssFeedState.Loading -> Loading()
//        is RssFeedState.Error -> Error()
//        is RssFeedState.Success -> HomeScreen(
//            state.data
//        )
//    }
//
//}

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


//@Preview(showBackground = true)
//@Composable
//fun RssCardItemPreview() {
//    RssappTheme {
//        RssCardItem(
////            rssItem = sampleRssItem()
//        )
//    }
//}


fun sampleRssItem() = RssItem(
    title = "An Introduction",
    description = "A small introduction into who I am and what plans I have for this blog",
    link = "https://mikewatkins.dev/blog/introduction/",
    guid = "https://mikewatkins.dev/blog/introduction",
    pubDate = "Sun, 13 Oct 2024 00:00:00 GMT"
)