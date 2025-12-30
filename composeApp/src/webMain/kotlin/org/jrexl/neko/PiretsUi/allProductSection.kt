package org.jrexl.neko.PiretsUi

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun allProductSection(onNavigate: (String) -> Unit) {
    Box(Modifier.fillMaxWidth().aspectRatio(9f / 16f)) {
        Text(text = "All Products")
    }
}