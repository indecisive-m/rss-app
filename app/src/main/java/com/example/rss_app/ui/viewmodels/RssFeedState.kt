package com.example.rss_app.ui.viewmodels

import com.example.rss_app.data.models.RssResponse

sealed class RssFeedState {
    object Loading : RssFeedState()
    data class Success(val data: RssResponse) : RssFeedState()
    data class Error(val error: Throwable) : RssFeedState()

}