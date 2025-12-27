package org.jrexl.neko.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jrexl.neko.ApiRoute.productRoute
import org.jrexl.neko.dataclass.Productdc


@Composable
fun ProductGridSection() {
    val creamBackground = Color(0xFFF9F7F2)
    val darkNavy = Color(0xFF0A192F)


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(creamBackground)
            .padding(vertical = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var productss by remember { mutableStateOf<List<Productdc>?>(emptyList()) }
        var loadings by remember { mutableStateOf(false) }
        var errors by remember { mutableStateOf<String?>(null) }
        LaunchedEffect(Unit){
            loadings =true
            try {
                productss = productRoute.getbestproduct()
            }catch (e:Exception){
                errors = e.message
            }
            finally {
                loadings = false
            }
        }

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

        when{

            loadings -> {
                Text("Loading...")
            }
            errors != null -> {
                Text("Error: $errors")
            }
            else -> {

                LazyRow(
                    // Adds padding to the start/end of the list content only
                    contentPadding = PaddingValues(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(24.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(productss ?: emptyList()) { product ->
                        pdcard(
                            product = product,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    }
                }

            }

        }
    }