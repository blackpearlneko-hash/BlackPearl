package org.jrexl.neko.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.jrexl.neko.ApiRoute.productRoute
import org.jrexl.neko.dataclass.Productdc


@Composable
fun ShopPage(){
    var products by remember { mutableStateOf<List<Productdc>?>(emptyList()) }
    var loading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }


    LaunchedEffect(Unit){
        loading =true
        try {
            products = productRoute.getproduct()
        }catch (e:Exception){
            error = e.message
        }
        finally {
            loading = false
        }
    }

    when{

        loading -> {
            Text("Loading...")
        }
        error != null -> {
            Text("Error: $error")
        }
        else -> {

            BoxWithConstraints() {
                val isMobile = maxWidth < 800.dp
                if (isMobile) {
                    ProductGridInternal(products, itemsPerRow = 2)
                } else {
                    ProductGridInternal(products, itemsPerRow = 5)
                }
            }
        }

        }
    }

@Composable
fun ProductGridInternal(products: List<Productdc>?, itemsPerRow: Int){
    Column(Modifier.padding(4.dp).fillMaxWidth()) {
        products?.chunked(itemsPerRow)?.forEach {
            rowItems ->
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                rowItems.forEach {
                    pdc ->
                    pdcard(product = pdc,
                        modifier = Modifier.weight(1f))

                }
                repeat(itemsPerRow - rowItems.size) {
                    Spacer(Modifier.weight(1f))
                }
            }
        }

    }
}

@Composable
fun pdcard(product: Productdc?, modifier: Modifier = Modifier) {
    var currentImageIndex by remember { mutableStateOf(0) }

    Column(modifier = modifier.padding(4.dp).border(1.dp, Color.Black).background(Color.White)) {
        Box(modifier = Modifier.fillMaxWidth().height(180.dp)){
            if (product?.images?.isNotEmpty() ?: false) {
                val imageUrl = product.images[currentImageIndex]

                val resource = asyncPainterResource(data = imageUrl)
                KamelImage(
                    resource = resource,
                    contentDescription = "Image",
                    modifier = Modifier.fillMaxSize(),
                    onLoading = { progress ->
                        // If this shows, Kamel is waiting for the server
                        CircularProgressIndicator()
                    },
                    onFailure = { exception ->
                        // If this shows, there is a CORS or 404 error
                        Icon(Icons.Default.Warning, contentDescription = "Error")
                        println("Kamel Error: ${exception.message}")
                    }
                )

                if (currentImageIndex > 0){
                    Text("<", modifier = Modifier.align(Alignment.CenterStart)
                        .padding(4.dp).clickable {
                            currentImageIndex--
                        })
                }
                product?.images?.size?.let { if (currentImageIndex < it - 1){
                    Text(">", modifier = Modifier.align(Alignment.CenterEnd).padding(4.dp).clickable {  currentImageIndex++ })
                } }


            }

        }
        Spacer(Modifier.height(8.dp))
        Text(
            product?.name ?: "",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        Text(
            text = "₹${product?.price ?: ""}",
            fontSize = 14.sp,
            color = Color(0xFF388E3C),
            modifier = Modifier.padding(8.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFA500)
                )
            ) {
                Text(
                    text = "Add Cart",
                    color = Color.White
                )
            }
            Spacer(Modifier.weight(1f))

            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFC107) // Yellow-Orange (Material Amber)
                )
            ) {
                Text(
                    text = "Buy Now",
                    color = Color.White
                )
            }
        }

    }

}


