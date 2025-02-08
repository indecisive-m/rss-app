package com.example.rss_app.ui.viewmodels

import androidx.core.text.parseAsHtml
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rss_app.data.network.RssServiceImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RssViewModel @Inject constructor(private val rssServiceImpl: RssServiceImpl) : ViewModel() {
    private val _rssFeeds = MutableStateFlow<RssFeedState>(RssFeedState.Loading)
    val rssFeeds: StateFlow<RssFeedState> = _rssFeeds.asStateFlow()


    init {
        fetchRssFeedsFromRssServiceImpl()
    }

    fun fetchRssFeedsFromRssServiceImpl() {
        viewModelScope.launch {
            try {
                _rssFeeds.value = RssFeedState.Loading

                val response = rssServiceImpl.fetchRssFeeds(
                    "https://mikewatkins.dev/rss.xml"

                )


                // "https://chriscoyier.net/feed/"


                _rssFeeds.value = RssFeedState.Success(response)

            } catch (error: Exception) {
                _rssFeeds.value = RssFeedState.Error(error)

            }
        }
    }


    private fun parseHTML(string: String?): String {

        return string?.parseAsHtml()
            .toString()
    }
}