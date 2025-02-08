package com.example.rss_app.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rss_app.Error
import com.example.rss_app.Loading
import com.example.rss_app.ui.components.RssItemList
import com.example.rss_app.ui.viewmodels.RssFeedState
import com.example.rss_app.ui.viewmodels.RssViewModel

@Composable
fun HomeScreen(
    viewModel: RssViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    val rssFeedState by viewModel.rssFeeds.collectAsState()


    when (val state = rssFeedState) {
        is RssFeedState.Loading -> Loading()
        is RssFeedState.Error -> Error()
        is RssFeedState.Success ->
            RssItemList(
                rssItemList = state.data.channel!!.item,
                modifier = modifier
            )
    }
}