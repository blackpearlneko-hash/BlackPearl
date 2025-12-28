package org.jrexl.neko.interfacefun

import org.jrexl.neko.ApiRoute.ApiClient

//suspend fun sendGoogleToken(token: String) {
//    ApiClient.googleLogin(mapOf("idToken" to token))
//}

interface SessionStore {
    fun save(token: String, name: String, email: String)
    fun clear()
    fun isLoggedIn(): Boolean
    fun firstName(): String?
    fun email(): String?
}