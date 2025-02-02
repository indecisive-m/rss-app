package com.example.rss_app.data.network

import com.example.rss_app.data.models.RssResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class RssServiceImpl @Inject constructor(private val httpClient: HttpClient) : RssService {
    override suspend fun fetchRssFeeds(url: String): RssResponse {

        val response: RssResponse = httpClient.get(url)
            .body()

        return response
    }

}