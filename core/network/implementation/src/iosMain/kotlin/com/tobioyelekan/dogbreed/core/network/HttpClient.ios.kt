package com.tobioyelekan.dogbreed.core.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin

actual val platformHttpClient: HttpClient = HttpClient(Darwin)