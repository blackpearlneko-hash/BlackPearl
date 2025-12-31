package org.jrexl.neko.ApiRoute



import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object KtorClient {
    private const val BASE_URL = "https://backend-t2gv.onrender.com"
//https://backend-t2gv.onrender.com
//    http://localhost:5000
    val httpClient = HttpClient {
        // 1. Configure the Base URL
        defaultRequest {
            url(BASE_URL)

        }

        // 2. Configure JSON
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }


    }
}
