package org.jrexl.neko

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import org.jrexl.neko.ui.AuthProvider

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport {
        AuthProvider {
            App()

        }
    }
}