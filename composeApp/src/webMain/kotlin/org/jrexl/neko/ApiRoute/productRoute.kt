package org.jrexl.neko.ApiRoute

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.path
import org.jrexl.neko.dataclass.Productdc

object productRoute {
    suspend fun getProduct(page: Int): List<Productdc> {
        return KtorClient.httpClient.get {
            url {
                path("product", "productdet")
                parameters.append("page", page.toString())
            }
            contentType(ContentType.Application.Json)
        }.body()
    }



}