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
import org.jrexl.neko.ui.AuthProvider


import org.jrexl.neko.ui.BrandStoryTeaserSection
import org.jrexl.neko.ui.FooterSection
import org.jrexl.neko.ui.HeroSection
import org.jrexl.neko.ui.Navbr
import org.jrexl.neko.ui.ProductGridSection
import org.jrexl.neko.ui.ShopPage
import org.jrexl.neko.ui.WhyChooseSection

// ... imports ...

@Composable
fun App() {
    MaterialTheme {
        AuthProvider {
        var currentScreen by remember { mutableStateOf("HOME") }
        var serverdata by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .safeContentPadding()
                .fillMaxSize()
                .verticalScroll(rememberScrollState()), // Global scroll for the whole page
        ) {
            Navbr(
                onNavigate = { screenName -> currentScreen = screenName }
            )

            Spacer(Modifier.height(20.dp))

            when (currentScreen) {
                "HOME" -> {
                    BrandStoryTeaserSection(serverdata)
                    Spacer(Modifier.height(20.dp))
                    ProductGridSection()
                    Spacer(Modifier.height(20.dp))
                    WhyChooseSection(serverdata)
                }
                "SHOP" -> {
                    ShopPage()
                }
                "ABOUT" -> {
                }
            }

            Spacer(Modifier.height(40.dp))
            FooterSection()
        }
    }}
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
// ./gradlew wasmJsBrowserDevelopmentRun
//  ./gradlew kotlinUpgradeYarnLock
// ./gradlew kotlinWasmUpgradeYarnLock
//  ./gradlew kotlinUpgradeYarnLock kotlinWasmUpgradeYarnLock clean wasmJsBrowserDevelopmentRun