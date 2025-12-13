package org.jrexl.neko.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import blackpearl.composeapp.generated.resources.Res
import blackpearl.composeapp.generated.resources.herosection
import org.jetbrains.compose.resources.painterResource

// --- 1. Main Entry Point ---
@Composable
fun BrandStoryTeaserSection(serverdata: Boolean) {
    // Define your colors (Navy/Brass theme)
    val creamBackground = Color(0xFFF9F7F2)

    // Replace R.drawable.artisan_brass with your actual image resource
    val imagePainter = painterResource(Res.drawable.herosection)

    BoxWithConstraints() {
        val isMobile = maxWidth < 800.dp
        if (isMobile) {
            MobileStoryLayout(imagePainter, creamBackground)
        } else {
            DesktopStoryLayout(imagePainter, creamBackground)
        }
    }

}

// --- 2. Desktop Layout (Side-by-Side) ---
@Composable
fun DesktopStoryLayout(
    imagePainter: androidx.compose.ui.graphics.painter.Painter,
    backgroundColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp) // Fixed height for immersive desktop feel
            .background(backgroundColor)
    ) {
        // Left: Image Block (50%)
        Image(
            painter = imagePainter,
            contentDescription = "Artisan working on brass",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        )

        // Right: Content Block (50%)
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(64.dp), // Generous padding for desktop
            contentAlignment = Alignment.CenterStart
        ) {
            StoryContent(isDesktop = true)
        }
    }
}

// --- 3. Mobile Layout (Stacked) ---
// --- Corrected Mobile Layout ---
@Composable
fun MobileStoryLayout(
    imagePainter: androidx.compose.ui.graphics.painter.Painter,
    backgroundColor: Color
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)

    ) {
        // Top: Image Block
        Image(
            painter = imagePainter,
            contentDescription = "Artisan working on brass",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp) // Ensure this height is fixed so it takes up space
        )

        // Bottom: Content Block
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 40.dp),
            contentAlignment = Alignment.Center
        ) {
            StoryContent(isDesktop = false)
        }
    }
}

// --- 4. Shared Content Component ---
@Composable
fun StoryContent(isDesktop: Boolean) {
    // Theme Colors
    val darkNavy = Color(0xFF0A192F)
    val agedBrass = Color(0xFFC5A059)

    Column(
        horizontalAlignment = if (isDesktop) Alignment.Start else Alignment.CenterHorizontally
    ) {
        // The Story Quote
        Text(
            text = buildAnnotatedString {
                append("Every piece tells a story—")
                withStyle(style = SpanStyle(fontStyle = FontStyle.Italic, color = agedBrass)) {
                    append("crafted by artisans")
                }
                append(", inspired by the sea, built to last.")
            },
            color = darkNavy,
            fontSize = if (isDesktop) 32.sp else 24.sp,
            fontFamily = MaterialTheme.typography.headlineLarge.fontFamily, // Replace with a Serif font if available
            fontWeight = FontWeight.Normal,
            lineHeight = if (isDesktop) 40.sp else 32.sp,
            textAlign = if (isDesktop) TextAlign.Start else TextAlign.Center,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // The CTA Button
        OutlinedButton(
            onClick = {  },
            shape = RectangleShape, // Sharp edges for "Premium" feel
            border = androidx.compose.foundation.BorderStroke(1.dp, darkNavy),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = darkNavy
            ),
            modifier = Modifier
                .then(if (isDesktop) Modifier else Modifier.fillMaxWidth()) // Full width on mobile
                .height(48.dp)
        ) {
            Text(
                text = "LEARN MORE ABOUT US",
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
        }
    }
}

