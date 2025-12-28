package org.jrexl.neko

import kotlinx.browser.window
import org.jrexl.neko.interfacefun.SessionStore

object BrowserSessionStore : SessionStore {

    override fun save(token: String, name: String, email: String) {
        window.localStorage.setItem("jwt", token)
        window.localStorage.setItem("name", name)
        window.localStorage.setItem("email", email)
    }

    override fun clear() {
        window.localStorage.clear()
    }

    override fun isLoggedIn(): Boolean {
        return window.localStorage.getItem("jwt") != null
    }

    override fun firstName(): String? {
        return window.localStorage.getItem("name")
            ?.split(" ")
            ?.firstOrNull()
    }

    override fun email(): String? {
return window.localStorage.getItem("email")
    }
}