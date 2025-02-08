package com.example.rss_app.domain.models

data class RssItem(
    val title: String,
    val description: String? = null,
    val link: String,
)
