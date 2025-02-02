package com.example.rss_app.data.network

import com.example.rss_app.data.models.RssResponse

interface RssService {
    suspend fun fetchRssFeeds(url: String): RssResponse
}