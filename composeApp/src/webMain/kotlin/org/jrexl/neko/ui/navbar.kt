package org.jrexl.neko.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.zIndex
import blackpearl.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.painterResource
import kotlin.math.roundToInt

// --- Global Colors ---
val DeepNavy = Color(0xFF0A192F)
val BrassGold = Color(0xFFD4AF37)
val OffWhite = Color(0xFFF8F8F8)

// Define the Menu Items logic ONCE (Data-Driven)
data class NavItem(val name: String, val action: () -> Unit)

@Composable
fun Navbr( onNavigate: (String) -> Unit  ) {
    val menuItems = remember {
        listOf(
            // 2. When Home is clicked, send "HOME" signal
            NavItem("Home") { onNavigate("HOME") },
            NavItem("Shop") { onNavigate("SHOP") },
            NavItem("About Us") { onNavigate("ABOUT") }
        )
    }}