package com.example.rss_app.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rss_app.data.network.RssServiceImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RssViewModel @Inject constructor(private val rssServiceImpl: RssServiceImpl) : ViewModel() {
    private val _rssfeeds = MutableStateFlow<RssFeedState>(RssFeedState.Loading)
    val rssfeeds = _rssfeeds.asStateFlow()

    fun fetchRssFeedsFromRssServiceImpl() {
        viewModelScope.launch {
            try {
                _rssfeeds.value = RssFeedState.Loading

                val response = rssServiceImpl.fetchRssFeeds("https://mikewatkins.dev/rss.xml")

                _rssfeeds.value = RssFeedState.Success(response)

            } catch (error: Exception) {
                _rssfeeds.value = RssFeedState.Error(error)

            }
        }
    }
}