package com.example.rss_app

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.rss_app.ui.theme.RssappTheme
import com.example.rss_app.ui.viewmodels.RssFeedState
import com.example.rss_app.ui.viewmodels.RssViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: RssViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        viewModel.fetchRssFeedsFromRssServiceImpl()

        lifecycleScope.launch() {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.rssfeeds.collect { state ->

                    when (state) {
                        is RssFeedState.Success -> Log.d(
                            "TEST",
                            "RSS Feed State: ${state.data.channel?.title}"
                        )

                        is RssFeedState.Loading -> Log.d(
                            "TEST",
                            "Loading"
                        )

                        else -> Log.d(
                            "TEST",
                            "Error"
                        )
                    }

                }
            }
        }


        setContent {
            RssappTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RssappTheme {
        Greeting("Android")
    }
}