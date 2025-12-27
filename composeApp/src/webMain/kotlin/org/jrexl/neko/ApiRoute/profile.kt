package org.jrexl.neko.ApiRoute

import io.ktor.client.request.*
import io.ktor.http.*

object ApiClient {

    suspend fun googleLogin(body: Map<String, String>) {
        KtorClient.httpClient.post("/api/auth/google") {
            contentType(ContentType.Application.Json)
            setBody(body)
        }
    }
}
