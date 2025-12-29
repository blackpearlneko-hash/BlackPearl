package org.jrexl.neko


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jrexl.neko.PiretsUi.homePageUi
import org.jrexl.neko.ui.*


@Composable
fun App() {



    MaterialTheme {

        var currentScreen by remember { mutableStateOf("HOME") }

        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .safeContentPadding()
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            Navbr(
                onNavigate = { screenName -> currentScreen = screenName }
            )

            Spacer(Modifier.height(20.dp))

            when (currentScreen) {
                "HOME" -> {
                    homePageUi()

                }
                "SHOP" -> {

                }
                "ABOUT" -> {
                }
            }
        }
    }}



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