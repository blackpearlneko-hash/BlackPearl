package org.jrexl.neko.ui

import androidx.compose.runtime.*
import org.jrexl.neko.ApiRoute.ApiClient
import org.jrexl.neko.vm.AuthContext
import org.jrexl.neko.vm.LocalAuthContext

@Composable
fun AuthProvider(content: @Composable () -> Unit) {

    val auth = remember { AuthContext() }

    LaunchedEffect(Unit) {
        try {
            val response = ApiClient.getme()
            auth.isLoggedIn = true
            auth.user = response.user
        } catch (e: Exception) {
            auth.isLoggedIn = false
        } finally {
            auth.loading = false
        }
    }

    CompositionLocalProvider(LocalAuthContext provides auth) {
        content()
    }
}