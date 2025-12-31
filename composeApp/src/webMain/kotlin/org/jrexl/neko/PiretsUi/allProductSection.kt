package org.jrexl.neko.PiretsUi

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import blackpearl.composeapp.generated.resources.Res
import blackpearl.composeapp.generated.resources.prctg
import blackpearl.composeapp.generated.resources.productbg
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.jetbrains.compose.resources.painterResource
import org.jrexl.neko.ApiRoute.productRoute
import org.jrexl.neko.dataclass.Productdc

@Composable
fun allProductSection(onNavigate: (String) -> Unit) {

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        val screenWidth = maxWidth
        val isMobile = screenWidth < 750.dp
        if (isMobile) {

            mobilehomepageviewProduct(onNavigate)
        }
        else{
            desktophomepageProduct()
        }
    }

}

@Composable
fun mobilehomepageviewProduct(onNavigate: (String) -> Unit){
    when {
        isTooSmallPhone() -> {
            UnsupportedDeviceScreen()
        }

        isLandscape() -> {
            RotateDeviceScreen()
        }

        else -> {
            mobilehomepageProduct(onNavigate) // your real UI
        }
    }
}


class ProductCard(product: Any) {

}

@Composable
fun mobilehomepageProduct(onNavigate: (String) -> Unit){

    val heightDp = screenheight()

    val basedep: Float = when (heightCategory(heightDp)) {

        HeightCategory.H640 -> 1.0f
        HeightCategory.H655 -> 1.30f
        HeightCategory.H670 -> 1.2f
        HeightCategory.H685 -> 1.2f
        HeightCategory.H700 -> 1.2f
        HeightCategory.H715 -> 1.2f
        HeightCategory.H730 -> 1.23f
        HeightCategory.H745 -> 1.7f
        HeightCategory.H760 -> 1.5f
        HeightCategory.H775 -> 1.9f
        HeightCategory.H790 -> 2.0f
        HeightCategory.H805 -> 1.26f
        HeightCategory.H820 -> 1.6f
        HeightCategory.H835 -> 1.7f
    }

    var currentPage by remember { mutableStateOf(1) }
    var products by remember { mutableStateOf<List<Productdc>>(emptyList()) }
    var loading by remember { mutableStateOf(false) }

    LaunchedEffect(currentPage) {
        loading = true
        val newProducts = productRoute.getProduct(currentPage)
        products =  newProducts
        println("FRONTEND PRODUCTS: $newProducts") // 👈 ADD THIS

        loading = false
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(9f / 16f)
    ) {

        // Background image
        Image(
            painter = painterResource(Res.drawable.productbg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // LEFT ARROW (Page Up / Previous)
        IconButton(
            onClick = {         if (currentPage > 1) currentPage--

            },
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 12.dp)
                .size(48.dp)
                .background(
                    color = Color.Black.copy(alpha = 0.4f),
                    shape = CircleShape
                )
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBackIos,
                contentDescription = "Previous",
                tint = Color.White
            )
        }

        // RIGHT ARROW (Page Down / Next)
        IconButton(
            onClick = {    currentPage++
            },
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 12.dp)
                .size(48.dp)
                .background(
                    color = Color.Black.copy(alpha = 0.4f),
                    shape = CircleShape
                )
        ) {
            Icon(
                imageVector = Icons.Default.ArrowForwardIos,
                contentDescription = "Next",
                tint = Color.White
            )
        }

        Box(
            modifier = Modifier
                .aspectRatio(9f / 16f)
                .align(Alignment.BottomCenter)
        ){

            Column {
                if (products.isEmpty()){
                    Text("OOps, no further products.",
                        color = Color(0xFFF6CF9A),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center)
                }
                Text("Dive in th ocean of antic",
                    color = Color(0xFFF5F5F5),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp)
                Spacer(Modifier.height((basedep * 55).dp))
                products
                    .take(6)                 // only 6 products
                    .chunked(2)              // 2 per row
                    .take(3)                 // max 3 rows
                    .forEach { rowItems ->

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {

                            rowItems.forEach { product ->
                                ProductCards(
                                    product = product,
                                    modifier = Modifier.weight(1f),
                                    basedep
                                )
                            }

                            // Fill empty space if only 1 item in row
                            if (rowItems.size == 1) {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }

                        Spacer(modifier = Modifier.height((basedep * 16).dp))
                    }
            }
        }}
    }



@Composable
fun ProductCards(
    product: Productdc,
    modifier: Modifier = Modifier,
    basedep: Float
) {

    Column(
        modifier = modifier
            .padding(2.dp)
    ) {

        val imageUrl = product.images.firstOrNull()

        if (imageUrl != null) {
            KamelImage(
                resource = asyncPainterResource(imageUrl),
                contentDescription = product.name,
                modifier = Modifier
                    .size(110.dp),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(Modifier.height((basedep * 3).dp))
        PriceTag(product.price)

    }
}

@Composable
fun PriceTag(price: Double) {
    Box(
        modifier = Modifier
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {

        // PRICE TAG PNG (background)
        Image(
            painter = painterResource(Res.drawable.prctg), // your PNG
            contentDescription = null,
            modifier = Modifier.width(100.dp).height(45.dp),
            contentScale = ContentScale.Fit
        )

        Text(
            text = "₹ $price",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
    }
}


@Composable
fun desktophomepageProduct(){

}