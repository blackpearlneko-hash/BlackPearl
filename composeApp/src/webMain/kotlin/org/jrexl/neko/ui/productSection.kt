package org.jrexl.neko.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import blackpearl.composeapp.generated.resources.Res
import blackpearl.composeapp.generated.resources.herosection
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

// --- 1. Data Model ---
data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val images: List<DrawableResource> // Changed from single image to List
)

// --- 2. Main Section Composable ---
@Composable
fun ProductGridSection(serverdata: Boolean) {
    // Theme Colors
    val creamBackground = Color(0xFFF9F7F2)
    val darkNavy = Color(0xFF0A192F)

    // Sample Data: Using a list of the same image for demo purposes
    // In a real app, you would pass different images here
    val sampleImages = listOf(
        Res.drawable.herosection,
        Res.drawable.herosection, // Duplicate for slider demo
        Res.drawable.herosection  // Duplicate for slider demo
    )

    val productList = listOf(
        Product(1, "Vintage Brass Telescope", "See the horizon clearly with this classic piece.", sampleImages),
        Product(2, "Nautical Brass Compass", "Find your true north.", sampleImages),
        Product(3, "Antique Ship Wheel", "Steer your own course.", sampleImages),
        Product(4, "Brass Lanterns & Bells", "Light up the dark seas.", sampleImages)
    )

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .background(creamBackground)
            .padding(vertical = 64.dp, horizontal = 24.dp)
    ) {
        // Fix: Define isMobile here so it captures 'maxWidth' from the Box scope
        val isMobile = maxWidth < 800.dp

        // Responsive Logic: 1 Column for Mobile (< 800dp), 2 for Desktop
        val columns = if (isMobile) 1 else 2

        // Calculate rows needed for the manual grid logic
        val rows = (productList.size + columns - 1) / columns

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            // Section Header
            Text(
                text = "Our Curated Brass Handicrafts\n& Nautical Wonders",
                color = darkNavy,
                fontSize = if (isMobile) 24.sp else 36.sp,
                lineHeight = if (isMobile) 32.sp else 44.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 48.dp)
            )

            // Manual Grid Implementation (Safe for Nested Scrolling)
            Column(
                verticalArrangement = Arrangement.spacedBy(48.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                for (i in 0 until rows) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(32.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        for (j in 0 until columns) {
                            val index = i * columns + j

                            // Check if a product exists for this slot
                            if (index < productList.size) {
                                Box(modifier = Modifier.weight(1f)) {
                                    ProductCard(productList[index], darkNavy)
                                }
                            } else {
                                // Important: Empty spacer to keep alignment in the last row if items are odd
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
            }
        }
    }
}

// --- 3. Individual Product Card (With Slider) ---
@OptIn(ExperimentalFoundationApi::class) // Needed for Pager in some versions
@Composable
fun ProductCard(product: Product, textColor: Color) {
    val agedBrass = Color(0xFFC5A059)

    // State for the Image Slider
    val pagerState = rememberPagerState(pageCount = { product.images.size })

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.LightGray.copy(alpha = 0.5f), RectangleShape)
            .padding(16.dp)
    ) {
        // Product Image Container (Square + Pager)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f) // Forces a square shape
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            // 1. Swipeable Image Slider
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { pageIndex ->
                Image(
                    painter = painterResource(product.images[pageIndex]),
                    contentDescription = "${product.name} image $pageIndex",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            // 2. Dots Indicator (Overlay at bottom)
            if (product.images.size > 1) {
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 12.dp)
                        // Add a subtle background pill behind dots for visibility
                        .background(Color.Black.copy(alpha = 0.2f), CircleShape)
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    repeat(product.images.size) { iteration ->
                        val isSelected = pagerState.currentPage == iteration
                        Box(
                            modifier = Modifier
                                .size(if (isSelected) 8.dp else 6.dp)
                                .clip(CircleShape)
                                .background(if (isSelected) Color.White else Color.White.copy(alpha = 0.5f))
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Product Name
        Text(
            text = product.name,
            color = textColor,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Short Description
        Text(
            text = product.description,
            color = Color.Gray,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Shop Now Button
        OutlinedButton(
            onClick = { /* TODO: Navigate to Product */ },
            shape = RectangleShape,
            border = androidx.compose.foundation.BorderStroke(1.dp, agedBrass),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = textColor
            ),
            modifier = Modifier.fillMaxWidth(0.7f)
        ) {
            Text(
                text = "SHOP NOW",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
        }
    }
}