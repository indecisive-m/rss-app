package com.example.rss_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.rss_app.data.models.RssItem
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
        is RssFeedState.Success -> Successful(
            state.data,
        )
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
fun Successful(
    state: RssResponse,
    modifier: Modifier = Modifier
) {

    val item = state.channel?.item ?: emptyList()

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 200.dp),
        contentAlignment = Alignment.Center
    ) {
        RssCardItem(
            item,
        )

    }
}


@Composable
fun RssCardItem(
    rssItem: List<RssItem>,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = "https://plus.unsplash.com/premium_photo-1680985551009-05107cd2752c?q=80&w=2532&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            contentDescription = null,
            modifier = Modifier
                .weight(1f)
                .size(150.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop


        )
        Spacer(modifier = modifier.width(16.dp))
        Column(
            modifier = modifier
                .weight(2f)
        ) {
            Text(
                text = rssItem[0].title.toString(),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier.height(8.dp))
            Text(
                text = rssItem[0].description.toString(),
                fontSize = 14.sp
            )
        }
    }
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