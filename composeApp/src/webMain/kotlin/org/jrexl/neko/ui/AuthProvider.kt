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

                if (response != null) {
                    auth.isLoggedIn = true
                    auth.user = response.user
                } else {
                    auth.isLoggedIn = false
                    auth.user = null
                }

            } catch (e: Exception) {
                auth.isLoggedIn = false
                auth.user = null
            } finally {
                auth.loading = false
            }
        }

    CompositionLocalProvider(LocalAuthContext provides auth) {
        content()
    }
}