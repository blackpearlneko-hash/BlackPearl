package org.jrexl.neko.dataclass

import kotlinx.serialization.Serializable

@Serializable
data class GoogleLoginResponse(
    val success: Boolean,
    val user: UserDto
)

@Serializable
data class UserDto(
    val name: String,
    val email: String
)

data class AuthState(
    val loading: Boolean = false,
    val isLoggedIn: Boolean = false,
    val user: UserDto? = null
)