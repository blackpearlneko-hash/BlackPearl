package org.jrexl.neko.interfacefun

import org.jrexl.neko.ApiRoute.ApiClient

suspend fun sendGoogleToken(token: String) {
    ApiClient.googleLogin(mapOf("idToken" to token))
}