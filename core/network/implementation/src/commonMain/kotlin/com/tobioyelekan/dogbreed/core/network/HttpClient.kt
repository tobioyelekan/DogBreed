package com.tobioyelekan.dogbreed.core.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

private const val BASE_URL = "https://dog.ceo/api/"

expect val platformHttpClient: HttpClient

fun httpClient(): HttpClient =
    platformHttpClient.config {
        expectSuccess = false
        defaultRequest { url(BASE_URL) }
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }
