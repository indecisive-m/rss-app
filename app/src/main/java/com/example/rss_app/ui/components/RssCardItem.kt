package com.example.rss_app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.text.parseAsHtml
import coil3.compose.AsyncImage
import com.example.rss_app.data.models.RssItem
import com.example.rss_app.ui.theme.bodyFontFamily

@Composable
fun RssCardItem(
    rssItem: RssItem,
    modifier: Modifier = Modifier
) {


    // Move this into the viewmodel in the future to decouple this

    val decode = rssItem.description?.parseAsHtml()



    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .height(150.dp)
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        Row(
            modifier = Modifier,
//                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = "https://plus.unsplash.com/premium_photo-1680985551009-05107cd2752c?q=80&w=2532&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                contentDescription = null,
                modifier = Modifier
                    .weight(1f)
                    .size(150.dp)
                    .clip(
                        RoundedCornerShape(
                            16.dp
                        )
                    ),
                contentScale = ContentScale.Crop


            )
            Spacer(modifier = modifier.width(16.dp))
            Column(
                modifier = modifier
                    .weight(2f)
                    .padding(end = 8.dp)
            ) {
                Text(
                    text = rssItem.title.toString(),
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    fontFamily = bodyFontFamily,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                )
                Spacer(modifier.height(8.dp))
                Text(
                    text = decode.toString(),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    fontFamily = bodyFontFamily,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer


                )
            }
        }

    }
}