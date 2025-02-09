package com.example.rss_app.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rss_app.data.models.RssItem


@Composable
fun RssItemList(
    rssItemList: List<RssItem>?,
    innerPaddingValues: PaddingValues,
    modifier: Modifier = Modifier
        .fillMaxSize()
) {

    if (rssItemList != null) {
        LazyColumn(
            modifier = modifier,
            contentPadding = PaddingValues(
                top = 12.dp,
                start = 12.dp,
                end = 12.dp,
                bottom = innerPaddingValues.calculateBottomPadding(),
            ),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(rssItemList) { rssItem ->
                RssCardItem(
                    rssItem = rssItem
                )

            }
        }

    } else {
        Loading()
    }
}