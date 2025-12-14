package org.jrexl.neko.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import blackpearl.composeapp.generated.resources.Res
import blackpearl.composeapp.generated.resources.bestsellersection
import blackpearl.composeapp.generated.resources.herosection
import blackpearl.composeapp.generated.resources.logo
import org.jetbrains.compose.resources.painterResource

// --- BRAND COLORS ---
private val NavyBlue = Color(0xFF0A192F)
private val AntiqueBrass = Color(0xFFD4AF37)

@Composable
fun HeroSection( serverdata: Boolean
) {
    BoxWithConstraints() {
        val isMobile = maxWidth < 800.dp
            if (isMobile) {
                MobileHeroContent(serverdata)
            } else {
                DesktopHeroContent(serverdata)
            }
    }
}


@Composable
private fun MobileHeroContent( serverdata: Boolean
) {
    Box(Modifier.fillMaxWidth().height(200.dp)) {
        if (serverdata){

        }
        else{
            Image(
                painter = painterResource(Res.drawable.bestsellersection),
                contentDescription = "best",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

    }
    Column(
        modifier = Modifier.fillMaxWidth().height(200.dp)
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Row(Modifier.padding(start = 20.dp)) {
            Text(
            "The best of 2025",
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp, // Desktop Size
            lineHeight = 34.sp,
            color = Color.Black,
        ) }


        Spacer(modifier = Modifier.height(24.dp))



        Row(Modifier.padding(start = 12.dp)) {
            Text(
                text = "Handcrafted brass decor, antique nautical instruments, and vintage treasures inspired by the spirit of the sea.",
                fontSize = 11.sp,
                color = Color.Black,
                lineHeight = 28.sp,
            )
        }

        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {},
            modifier = Modifier.padding(start = 25.dp),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(Color.Blue),
        ){
            Text("Shop Now")
        }
        Spacer(modifier = Modifier.weight(1f))


    }
}

@Composable
private fun DesktopHeroContent( serverdata : Boolean
) {
    Box(Modifier.fillMaxWidth().height(400.dp)) {
        if (serverdata){

        }
        else{
            Image(
                painter = painterResource(Res.drawable.herosection),
                contentDescription = "herosec",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

    }
    Column(
        modifier = Modifier.fillMaxWidth().height(400.dp)
    ) {
        Spacer(modifier = Modifier.weight(1f))
        // Title (Larger for Desktop)
        Row(Modifier.padding(start = 35.dp)) {   Text(
            "The best of 2025",
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            fontSize = 56.sp, // Desktop Size
            lineHeight = 64.sp,
            color = Color.Black,
        ) }


        Spacer(modifier = Modifier.height(32.dp))

Row(Modifier.padding(start = 15.dp)) {
    Text(
        text = "Handcrafted brass decor, antique nautical instruments, and vintage treasures inspired by the spirit of the sea.",
        fontSize = 18.sp,
        color = Color.Black,
        lineHeight = 28.sp,
        modifier = Modifier.widthIn(max = 600.dp)
    )
}



        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {},
            modifier = Modifier.padding(start = 25.dp),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(Color.Blue),
        ){
            Text("Shop Now")
        }
        Spacer(modifier = Modifier.weight(1f))



    }
}

