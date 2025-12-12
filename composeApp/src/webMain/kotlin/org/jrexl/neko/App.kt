package org.jrexl.neko

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource

import blackpearl.composeapp.generated.resources.Res
import blackpearl.composeapp.generated.resources.compose_multiplatform
import org.jrexl.neko.ui.HeroSection
import org.jrexl.neko.ui.Navbr

@Composable
fun App() {
    MaterialTheme {
        var serverdata by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            Navbr(serverdata)
            Spacer(Modifier.height(20.dp))
            HeroSection(serverdata)
        }
    }
}


@Composable
fun ResponsiveVisibility(
    breakPoint: androidx.compose.ui.unit.Dp = 800.dp,
    desktopContent: @Composable () -> Unit,
    mobileContent: @Composable () -> Unit
) {
    BoxWithConstraints {
        if (maxWidth < breakPoint) {
            mobileContent()
        } else {
            desktopContent()
        }
    }
}

// ./gradlew wasmJsBrowserDevelopmentRun -t