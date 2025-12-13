package org.jrexl.neko.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// --- 1. Data Model ---
data class UspItem(
    val title: String,
    val icon: ImageVector
)

// --- 2. Main Section Composable ---
@Composable
fun WhyChooseSection(serverdata : Boolean) {
    // Theme Colors
    val darkNavy = Color(0xFF0A192F)
    val agedBrass = Color(0xFFC5A059)
    val softWhite = Color(0xFFE0E0E0)

    // Data List (Using Material Icons as placeholders)
    val uspList = listOf(
        UspItem("Authentic Craftsmanship", Icons.Outlined.Build), // Hammer/Build
        UspItem("Maritime-inspired Decor", Icons.Outlined.Anchor), // Anchor (if avail) or Water
        UspItem("Handmade Artifacts", Icons.Outlined.Fingerprint), // Hand/Touch
        UspItem("Free Shipping Over 500", Icons.Outlined.LocalShipping), // Truck
        UspItem("Eco-friendly Brass", Icons.Outlined.Eco) // Leaf
    )

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .background(darkNavy) // Dark background for contrast
            .padding(vertical = 80.dp, horizontal = 24.dp)
    ) {
        // RESPONSIVE LOGIC:
        // Desktop: 3 Columns
        // Mobile: 2 Columns (Compact grid)
        val isMobile = maxWidth < 800.dp
        val columns = if (isMobile) 2 else 3

        // Calculate rows
        val rows = (uspList.size + columns - 1) / columns

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            // --- HEADER ---
            Text(
                text = "Why Choose Black Pearl?",
                color = agedBrass, // Gold Title
                fontSize = if (isMobile) 28.sp else 36.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 64.dp)
            )

            // --- MANUAL GRID LOOP ---
            Column(
                verticalArrangement = Arrangement.spacedBy(if (isMobile) 48.dp else 64.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                for (i in 0 until rows) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(32.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        for (j in 0 until columns) {
                            val index = i * columns + j

                            if (index < uspList.size) {
                                Box(modifier = Modifier.weight(1f)) {
                                    UspCard(uspList[index], agedBrass, softWhite)
                                }
                            } else {
                                // Spacer for empty slots (keeps alignment in last row)
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
            }
        }
    }
}

// --- 3. Individual USP Item Card ---
@Composable
fun UspCard(item: UspItem, iconColor: Color, textColor: Color) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        // Icon Circle Container
        Box(
            modifier = Modifier
                .size(64.dp)
                .border(1.dp, iconColor.copy(alpha = 0.5f), CircleShape) // Thin gold border
                .background(Color.White.copy(alpha = 0.05f), CircleShape), // Subtle glow
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(32.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Title Text
        Text(
            text = item.title,
            color = textColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            lineHeight = 22.sp
        )
    }
}