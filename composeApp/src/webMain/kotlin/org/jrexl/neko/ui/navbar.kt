package org.jrexl.neko.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.sp
import blackpearl.composeapp.generated.resources.Res
import blackpearl.composeapp.generated.resources.logo
import org.jetbrains.compose.resources.painterResource
import kotlin.math.roundToInt



val DeepNavy = Color(0xFF0A192F)
val BrassGold = Color(0xFFD4AF37)
val OffWhite = Color(0xFFF8F8F8)
@Composable
fun Navbr(){



    Column(modifier = Modifier.fillMaxWidth()) {

        PromoBanner()
        Surface(color = DeepNavy,
            shadowElevation = 8.dp,
            modifier = Modifier.fillMaxWidth().height(80.dp)
        ){
            Row(Modifier.fillMaxSize().padding(horizontal = 20.dp), verticalAlignment = Alignment.CenterVertically) {
                Spacer(Modifier.weight(1f))
                LogoScreen()
                Spacer(Modifier.width(30.dp))

                Row(Modifier.wrapContentSize()) {
                    navtext("Home"){

                    }
                    Spacer(Modifier.width(20.dp))


                    navtext("Shop"){

                    }
                    Spacer(Modifier.width(20.dp))

                    navtext("About Us"){

                    }
                    Spacer(Modifier.width(20.dp))

                }

                Spacer(Modifier.weight(1f))

                Ordernav("Cart"){

                }
                Spacer(Modifier.width(20.dp))

                signin("Sign In")
                Spacer(Modifier.width(20.dp))





            }

        }

    }
}


@Composable
fun Ordernav(name: String, function: () -> Unit) {
    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = BrassGold,
                shape = RoundedCornerShape(50) // Pill shape
            )
            .clickable {  }
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Outlined.ShoppingCart,
                contentDescription = "Cart",
                tint = BrassGold,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = name.uppercase(),
                color = BrassGold,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }
    }
}


@Composable
fun signin(name: String) {
    Button(
        onClick = {  },
        colors = ButtonDefaults.buttonColors(
            containerColor = BrassGold, // Solid Gold Background
            contentColor = DeepNavy     // Navy Text
        ),
        shape = RoundedCornerShape(50), // Pill shape matches Cart
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
        modifier = Modifier.height(42.dp)
    ) {
        if (name == "Sign In"){
            Text(
                text = name.uppercase(),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 14.sp,
                letterSpacing = 1.sp,
                modifier = Modifier.clickable {  }

            )
        }
        else{
            Text(
                text = "",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 14.sp,
                letterSpacing = 1.sp,
                modifier = Modifier.clickable {  }


            )
        }

    }
}


@Composable
fun navtext(name: String, function: () -> Unit) {
    Box(
        modifier = Modifier
            .clickable(onClick = function)
            .padding(horizontal = 16.dp, vertical = 8.dp), // Generous padding for easier clicking
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = name.uppercase(),
            color = OffWhite,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp,    // Adds a bit of elegance
            fontFamily = FontFamily.SansSerif
        )
    }
}


@Composable
fun LogoScreen(){
    Image(
        painter = painterResource(resource = Res.drawable.logo),
        contentDescription = null,
        modifier = Modifier.size(100.dp),
    )

}


@Composable
fun PromoBanner(){
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
            text = "Hello from Pearl",
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