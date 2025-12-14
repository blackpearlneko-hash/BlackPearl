package org.jrexl.neko.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val images: List<DrawableResource>
)

@Composable
fun ProductGridSection(serverdata: Boolean) {
    // Theme Colors
    val creamBackground = Color(0xFFF9F7F2)
    val darkNavy = Color(0xFF0A192F)

    // Sample Data
    val sampleImages = listOf(
        Res.drawable.herosection,
        Res.drawable.herosection,
        Res.drawable.herosection
    )

    val productList = listOf(
        Product(1, "Vintage Brass Telescope", "See the horizon clearly with this classic piece.", sampleImages),
        Product(2, "Nautical Brass Compass", "Find your true north.", sampleImages),
        Product(3, "Antique Ship Wheel", "Steer your own course.", sampleImages),
        Product(4, "Brass Lanterns & Bells", "Light up the dark seas.", sampleImages),
        Product(5, "Captain's Logbook", "Record your daily journey.", sampleImages) // Added one more to test scrolling
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(creamBackground)
            .padding(vertical = 64.dp), // Removed horizontal padding here to let scroll touch edges
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Section Header
        Text(
            text = "Best Sell of 2025\nOur Curated Brass Handicrafts & Nautical Wonders",
            color = darkNavy,
            fontSize = 32.sp, // Simplified sizing
            lineHeight = 40.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(bottom = 48.dp)
        )

        LazyRow(
            // Adds padding to the start/end of the list content only
            contentPadding = PaddingValues(horizontal = 24.dp),
            // Adds space between items
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(productList) { product ->
                // WRAPPER: We must give the card a fixed width in a horizontal row
                // otherwise it might try to be too thin or too wide.
                Box(modifier = Modifier.width(320.dp)) {
                    ProductCard(product, darkNavy)
                }
            }
        }
    }
}

// --- 3. Individual Product Card (Unchanged) ---
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductCard(product: Product, textColor: Color) {
    val agedBrass = Color(0xFFC5A059)
    val pagerState = rememberPagerState(pageCount = { product.images.size })

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth() // This now fills the 320.dp wrapper box
            .border(1.dp, Color.LightGray.copy(alpha = 0.5f), RectangleShape)
            .padding(16.dp)
    ) {
        // Image Container
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { pageIndex ->
                Image(
                    painter = painterResource(product.images[pageIndex]),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            if (product.images.size > 1) {
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 12.dp)
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

        Text(
            text = product.name,
            color = textColor,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(8.dp))

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

        OutlinedButton(
            onClick = { },
            shape = RectangleShape,
            border = androidx.compose.foundation.BorderStroke(1.dp, agedBrass),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = textColor),
            modifier = Modifier.fillMaxWidth(0.7f)
        ) {
            Text("SHOP NOW", fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }
    }
}