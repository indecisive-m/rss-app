package com.example.rss_app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.xml.xml
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object KtorModule {

    @Provides
    @Singleton

    fun baseClient(): HttpClient {
        return HttpClient {
            install(Logging) {
                logger = Logger.ANDROID
                level = LogLevel.BODY
            }
            install(ContentNegotiation) {
                xml()
            }
        }
    }

}