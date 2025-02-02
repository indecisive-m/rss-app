package com.example.rss_app.data

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.*
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse

suspend fun ktorFetch() {

    val client = HttpClient(Android)

    val response: HttpResponse = client.get("https://mikewatkins.dev/rss.xml")

    client.close()

}
