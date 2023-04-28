package data.remote.service

import data.remote.response.image.ImageResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.http.encodedPath
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

abstract class KtorApi {
    val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
    }


    fun HttpRequestBuilder.pathUrl(path: String) {
        url {
            takeFrom("https://api.unsplash.com/")
//            path("3", path)
//            parameter("api_key", API_KEY)
        }
    }
}

class ImageServiceImpl  {

    private val client = HttpClient{
        expectSuccess = true
        install(HttpTimeout) {
            val timeout = 30000L
            connectTimeoutMillis = timeout
            requestTimeoutMillis = timeout
            socketTimeoutMillis = timeout
        }

        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }

        install(Logging) {
            logger = object: Logger {
                override fun log(message: String) {
                    println("HTTP Client: $message")
                }
            }
            level = LogLevel.HEADERS
        }
    }

    private fun HttpRequestBuilder.quote(path: String) {
        url {
            takeFrom("https://api.unsplash.com/")
            encodedPath = path
        }
    }

     suspend fun getQuoteWithImage(
        query: String,
//        numPerPage: Int,
//        page: Int,
//        orientation: String
    ): ImageResponse {
        return client.get {
            quote("search/photos?query=quote&orientation=landscape&per_page=10&client_id=oQG-FbLBgfmCgktGaeJwDfBpAVxSGQxY9QOe1PWYges")
        }.body()
    }
}