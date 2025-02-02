package com.example.rss_app.data.network

import com.example.rss_app.data.models.RssResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import nl.adaptivity.xmlutil.serialization.XML
import javax.inject.Inject

class RssServiceImpl @Inject constructor(private val httpClient: HttpClient) : RssService {
    override suspend fun fetchRssFeeds(url: String): RssResponse {


        val response = httpClient.get(url)
            .bodyAsText()


        val rssResponse = XML.decodeFromString<RssResponse>(response)

        return rssResponse

    }

}