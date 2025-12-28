package org.jrexl.neko.ui

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.zIndex
import blackpearl.composeapp.generated.resources.Res
import blackpearl.composeapp.generated.resources.logo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jrexl.neko.ApiRoute.ApiClient
import org.jrexl.neko.Auth.googleLogin
import org.jrexl.neko.vm.LocalAuthContext
import kotlin.math.roundToInt

// --- Global Colors ---
val DeepNavy = Color(0xFF0A192F)
val BrassGold = Color(0xFFD4AF37)
val OffWhite = Color(0xFFF8F8F8)

// Define the Menu Items logic ONCE (Data-Driven)
data class NavItem(val name: String, val action: () -> Unit)

@Composable
fun Navbr( onNavigate: (String) -> Unit  ) {
    // 1. Define your links here
    val menuItems = remember {
        listOf(
            // 2. When Home is clicked, send "HOME" signal
            NavItem("Home") { onNavigate("HOME") },
            NavItem("Shop") { onNavigate("SHOP") },
            NavItem("About Us") { onNavigate("ABOUT") }
        )
    }

    // 2. State to control the Drawer
    var isDrawerOpen by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth().zIndex(10f)) {
        PromoBanner()

        Surface(
            color = DeepNavy,
            shadowElevation = 8.dp,
            modifier = Modifier.fillMaxWidth().height(80.dp)
        ) {
            BoxWithConstraints(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                val screenWidth = maxWidth
                val isMobile = screenWidth < 900.dp

                // --- NAVBAR CONTENT ---
                Row(
                    modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // LEFT: Logo
                    LogoScreen()

                    // CENTER & RIGHT
                    if (isMobile) {
                        // --- MOBILE VIEW ---
                        Spacer(Modifier.weight(1f)) // Push everything to right

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Ordernav("Cart") { }
                            Spacer(Modifier.width(16.dp))

                            // MENU ICON: Clicks open the drawer
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu",
                                tint = BrassGold,
                                modifier = Modifier
                                    .size(32.dp)
                                    .clickable { isDrawerOpen = true }
                            )
                        }
                    } else {
                        // --- DESKTOP VIEW ---
                        // Center Links
                        Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                            menuItems.forEach { item ->
                                navtext(item.name, item.action)
                            }
                        }

                        // Right Buttons
                        Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                            Ordernav("Cart") { }
                            signin("Sign In")
                        }
                    }
                }
            }
        }
    }

    // 3. THE DRAWER POPUP
    // This sits "outside" the normal layout flow, on top of everything
    if (isDrawerOpen) {
        MobileDrawer(
            items = menuItems,
            onClose = { isDrawerOpen = false }
        )
    }
}

// --- NEW COMPOSABLE: The Side Drawer ---
@Composable
fun MobileDrawer(items: List<NavItem>, onClose: () -> Unit) {
    // Popup allows us to break out of the 80dp Navbar height
    Popup(
        alignment = Alignment.TopEnd,
        onDismissRequest = onClose // Close if they click outside
    ) {
        // A. The Scrim (Semi-transparent dark background)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
                .clickable { onClose() } // Clicking background closes it
        ) {
            // B. The Actual Sidebar (White/Navy Panel)
            Column(
                modifier = Modifier
                    .align(Alignment.CenterEnd) // Slide in from Right
                    .fillMaxHeight()
                    .width(280.dp) // Width of the sidebar
                    .background(DeepNavy)
                    .border(1.dp, BrassGold) // Nice gold border
                    .clickable(enabled = false) {} // Prevent clicks passing through sidebar
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                // Header: Close Button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                        tint = BrassGold,
                        modifier = Modifier
                            .size(32.dp)
                            .clickable { onClose() }
                    )
                }

                Spacer(Modifier.height(40.dp))

                // The Links
                items.forEach { item ->
                    Text(
                        text = item.name.uppercase(),
                        color = OffWhite,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                item.action()
                                onClose() // Close drawer after clicking link
                            }
                            .padding(vertical = 16.dp)
                    )
                    // Divider line
                    HorizontalDivider(color = BrassGold.copy(alpha = 0.3f))
                }

                Spacer(Modifier.weight(1f))

                // Bottom: Sign In Button in Drawer
                signin("Sign In")
            }
        }
    }
}

// --- HELPERS (Unchanged) ---
@Composable
fun Ordernav(name: String, function: () -> Unit) {
    Box(
        modifier = Modifier
            .border(1.dp, BrassGold, RoundedCornerShape(50))
            .clickable(onClick = function)
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Outlined.ShoppingCart, "Cart", tint = BrassGold, modifier = Modifier.size(18.dp))
            Spacer(Modifier.width(8.dp))
            Text(name.uppercase(), color = BrassGold, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        }
    }
}







@Composable
fun signin(name: String) {
    val auth = LocalAuthContext.current
    val scope = rememberCoroutineScope()

    val buttonText = if (auth.isLoggedIn) "Log Out" else name

    Button(
        onClick = {
            if (auth.isLoggedIn) {
                // LOGOUT
                scope.launch {
                    ApiClient.logout()
                    auth.isLoggedIn = false
                    auth.user = null
                }
            } else {
                // LOGIN
                googleLogin { token ->
                    scope.launch {
                        ApiClient.googleLogin(mapOf("idToken" to token))
                        val me = ApiClient.getme()
                        auth.isLoggedIn = true
                        auth.user = me.user
                    }
                }
            }
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = BrassGold,
            contentColor = DeepNavy
        ),
        shape = RoundedCornerShape(50),
        modifier = Modifier.height(42.dp)
    ) {
        Text(buttonText.uppercase())
    }
}


@Composable
fun navtext(name: String, function: () -> Unit) {
    Box(
        modifier = Modifier.clickable(onClick = function).padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(name.uppercase(), color = OffWhite, fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun LogoScreen() {
    Image(
        painter = painterResource(Res.drawable.logo),
        contentDescription = "Logo",
        modifier = Modifier.size(100.dp) // Adjusted for Navbar fit
    )
}

@Composable
fun PromoBanner() {
    // (Keep your existing PromoBanner code here - omitted for brevity)
    // Just paste your previous PromoBanner function at the bottom
    val density = LocalDensity.current

    // 1. Measure the container width
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
            .background(BrassGold)
    ) {
        val containerWidthPx = with(density) { maxWidth.toPx() }
        var textWidthPx by remember { mutableStateOf(0f) }

        // 2. Set up the Infinite Animation
        val infiniteTransition = rememberInfiniteTransition()

        // Calculate the target: The rightmost position where text stops
        // If text is wider than screen, this math ensures it bounces correctly within view
        val maxOffset = if (containerWidthPx > textWidthPx) {
            containerWidthPx - textWidthPx
        } else {
            0f
        }

        // 3. Animate the Offset X value
        val offsetX by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = maxOffset,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 15000, easing = LinearEasing), // 5 seconds one way
                repeatMode = RepeatMode.Reverse // This makes it go Left -> Right -> Left
            )
        )

        // 4. Render the Text with the animated offset
        Text(
            text = "Black Pearl, Where Every Piece Has Sailed Through Time",
            style = MaterialTheme.typography.bodySmall,
            color = DeepNavy,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            modifier = Modifier
                .align(Alignment.CenterStart) // Start aligned to the left
                .offset { IntOffset(x = offsetX.roundToInt(), y = 0) } // Apply animation
                .onSizeChanged { size ->
                    textWidthPx = size.width.toFloat() // Capture text width
                }
        )
    }
}