package org.jrexl.neko.ApiRoute

import io.ktor.client.request.*
import io.ktor.client.call.*
import io.ktor.http.*
import org.jrexl.neko.dataclass.GoogleLoginResponse

object ApiClient {

    suspend fun googleLogin(body: Map<String, String>): GoogleLoginResponse {
        return KtorClient.httpClient.post("/api/auth/google") {
            contentType(ContentType.Application.Json)
            setBody(body)
        }.body()
    }

    suspend fun getme(): GoogleLoginResponse{
        return KtorClient.httpClient.get("/api/auth/me").body<GoogleLoginResponse>()
    }

    suspend fun logout(){

        KtorClient.httpClient.post("/api/auth/logout")
    }
}
