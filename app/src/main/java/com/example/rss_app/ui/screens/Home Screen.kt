package com.example.rss_app.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rss_app.Error
import com.example.rss_app.ui.components.FeedHeader
import com.example.rss_app.ui.components.Loading
import com.example.rss_app.ui.components.RssItemList
import com.example.rss_app.ui.viewmodels.RssFeedState
import com.example.rss_app.ui.viewmodels.RssViewModel

@Composable
fun HomeScreen(
    viewModel: RssViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {

    val rssFeedState by viewModel.rssFeeds.collectAsState()

    Scaffold(
        topBar = { FeedHeader() },
        containerColor = MaterialTheme.colorScheme.primary
    ) { innerPadding ->

        when (val state = rssFeedState) {
            is RssFeedState.Loading -> Loading(modifier = modifier.padding(innerPadding))
            is RssFeedState.Error -> Error(modifier = modifier.padding(innerPadding))
            is RssFeedState.Success ->
                RssItemList(
                    rssItemList = state.data.channel?.item,
                    innerPaddingValues = innerPadding,
                    modifier = modifier
                        .fillMaxSize()
                        .padding(
                            top = innerPadding.calculateTopPadding(),
                        )
                )
        }


    }


}