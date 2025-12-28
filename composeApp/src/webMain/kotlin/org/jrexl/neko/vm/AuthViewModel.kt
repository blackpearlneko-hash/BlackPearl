package org.jrexl.neko.vm

import androidx.compose.runtime.*
import org.jrexl.neko.dataclass.UserDto

class AuthContext {
    var loading by mutableStateOf(true)
    var isLoggedIn by mutableStateOf(false)
    var user by mutableStateOf<UserDto?>(null)
}

val LocalAuthContext = staticCompositionLocalOf<AuthContext> {
    error("AuthContext not provided")
}