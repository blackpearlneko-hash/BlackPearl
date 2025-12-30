package org.jrexl.neko.PiretsUi

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import blackpearl.composeapp.generated.resources.Res
import blackpearl.composeapp.generated.resources.cll
import blackpearl.composeapp.generated.resources.eml
import blackpearl.composeapp.generated.resources.facebook
import blackpearl.composeapp.generated.resources.feather
import blackpearl.composeapp.generated.resources.insta
import blackpearl.composeapp.generated.resources.jrexl
import blackpearl.composeapp.generated.resources.logolo
import blackpearl.composeapp.generated.resources.mobilehome
import blackpearl.composeapp.generated.resources.signin
import blackpearl.composeapp.generated.resources.twitter
import blackpearl.composeapp.generated.resources.youtube
import org.jetbrains.compose.resources.painterResource



enum class HeightCategory {
    H640,
    H655,
    H670,
    H685,
    H700,
    H715,
    H730,
    H745,
    H760,
    H775,
    H790,
    H805,
    H820,
    H835
}

@Composable
fun homePageUi(    onNavigate: (String) -> Unit
) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        val screenWidth = maxWidth
        val isMobile = screenWidth < 750.dp
        if (isMobile) {

            mobilehomepageview(onNavigate)
        }
        else{
            desktophomepage()
        }
    }

}


@Composable
fun mobilehomepageview(onNavigate: (String) -> Unit){
    when {
        isTooSmallPhone() -> {
            UnsupportedDeviceScreen()
        }

        isLandscape() -> {
            RotateDeviceScreen()
        }

        else -> {
            mobilehomepage(onNavigate) // your real UI
        }
    }
}


@Composable
fun mobilehomepage(onNavigate: (String) -> Unit){




    val offsetX = remember { Animatable(0f) }
    val leftLimit = -200f
    val rightLimit = 250f
    val TitleGold = Color(0xFFF6CF9A)
    val TextGold = Color(0xFFE8D8B5)
    val DividerGold = Color(0xFF8A6B3E)
    val FooterGold = Color(0xFFF6CF9A)
    val FooterMuted = Color(0xFFE8D8B5)

    val heightDp = screenheight()
    val ratio = heightWidthRatio()

    val basedep: Float = when (heightCategory(heightDp)) {

        HeightCategory.H640 -> 1.0f
        HeightCategory.H655 -> 1.30f
        HeightCategory.H670 -> 1.2f
        HeightCategory.H685 -> 1.3f
        HeightCategory.H700 -> 1.2f
        HeightCategory.H715 -> 1.2f
        HeightCategory.H730 -> 1.23f
        HeightCategory.H745 -> 1.7f
        HeightCategory.H760 -> 1.5f
        HeightCategory.H775 -> 1.9f
        HeightCategory.H790 -> 2.0f
        HeightCategory.H805 -> 1.26f
        HeightCategory.H820 -> 2.2f
        HeightCategory.H835 -> 1.7f
    }


    LaunchedEffect(Unit) {
        while (true) {
            // Move Right ➡️
            offsetX.animateTo(
                targetValue = rightLimit,
                animationSpec = tween(
                    durationMillis = 6000,
                    easing = LinearEasing
                )
            )

            // Move Left ⬅️
            offsetX.animateTo(
                targetValue = leftLimit,
                animationSpec = tween(
                    durationMillis = 6000,
                    easing = LinearEasing
                )
            )
        }
    }

    Box(Modifier.fillMaxWidth().aspectRatio( 9f / 16f
    )) {
        Image(
            painter = painterResource(Res.drawable.mobilehome),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .align(Alignment.BottomCenter)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color(0xFF132B3A),
                            Color(0xFF0B1C2D)
                        )
                    )
                )
        )


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopStart),
            verticalArrangement = Arrangement.Top
        ) {
        Text(
            text = "Black Pearl, Where Every Piece Has Sailed Through Time",
            color = Color(0xFFE6D3A3),
            fontSize = 10.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(top = 65.dp)
                .offset { IntOffset(offsetX.value.toInt(), 0) }
        )
            Spacer(Modifier.height((basedep * 15).dp))

            Image(
                painter = painterResource(Res.drawable.logolo),
                contentDescription = "logo",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(110.dp).padding(start = 1.dp)
            )
            Spacer(Modifier.height((basedep * 30).dp))

            Row(
                modifier = Modifier
                    .clickable {  }
                    .padding(end = 10.dp),
            ) {
                Spacer(Modifier.weight(1f))
                Column(Modifier.clickable(onClick = {  })) {
                    Text(
                    text = "Know Me",
                    fontFamily = FontFamily.Cursive,
                        fontStyle = FontStyle.Italic,
                    fontSize = 12.sp,
                    letterSpacing = 0.6.sp,
                    color = Color(0xFFF6CF9A)
                )
                    Image(
                        painter = painterResource(Res.drawable.feather),
                        contentDescription = "know me",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.size(40.dp).padding(start = 16.dp)
                    )
                }
            }
            Spacer(Modifier.height((basedep * 70).dp))



            Row {
                Text(
                    text = "Explore",
                    modifier = Modifier.padding(start = 30.dp).clickable(onClick = {
                        onNavigate("PRODUCT")

                    }),
                    fontFamily = FontFamily.Cursive,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = Color(0xFFF6CF9A),
                )
Spacer(Modifier.width(50.dp))
                Text(
                    text = "Treasure!",
                    modifier = Modifier.clickable(onClick = {  }),
                    fontFamily = FontFamily.Cursive,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(0xFFF6CF9A),
                )


            }
            Spacer(Modifier.height((basedep * 5).dp))


            Row {
                Spacer(Modifier.weight(1f))
                Image(
                    painter = painterResource(Res.drawable.signin),
                    contentDescription = "Image Button",
                    modifier = Modifier
                        .clickable {  }
                )
                Spacer(Modifier.weight(1f))

            }

//            Text(basedep.toString(),
//                fontFamily = FontFamily.Cursive,
//                fontStyle = FontStyle.Italic,
//                fontWeight = FontWeight.Bold,
//                fontSize = 15.sp,
//                color = Color(0xFFF6CF9A),)
//            Text(heightDp.toString(),
//                fontFamily = FontFamily.Cursive,
//                fontStyle = FontStyle.Italic,
//                fontWeight = FontWeight.Bold,
//                fontSize = 15.sp,
//                color = Color(0xFFF6CF9A),)
//            Text(ratio.toString(),
//                fontFamily = FontFamily.Cursive,
//                fontStyle = FontStyle.Italic,
//                fontWeight = FontWeight.Bold,
//                fontSize = 15.sp,
//                color = Color(0xFFF6CF9A),)

            Row {
                Spacer(Modifier.width(14.dp))
                Column {
                    Text(
                        text = "Contact Us",
                        color = TitleGold,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                    )


                    Box(
                        modifier = Modifier
                            .width(80.dp)
                            .height(1.dp)
                            .background(DividerGold)
                    )


                    Row(Modifier.clickable(onClick = {  }),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.eml),
                            contentDescription = "email image",
                            modifier = Modifier.size(12.dp)
                        )


                        Text(
                            text = "blackpearl.neko@gmail.com",
                            color = TextGold,
                            fontSize = 8.sp
                        )
                    }


                    // 🔹 Phone Row
                    Row(Modifier.clickable(onClick = {  }),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.cll),
                            contentDescription = "phone image",
                            modifier = Modifier.size(12.dp)
                        )

                        Spacer(Modifier.width(8.dp))

                        Text(
                            text = "+1 (800) 123-4567",
                            color = TextGold,
                            fontSize = 8.sp
                        )
                    }
                }
                Spacer(Modifier.width(8.dp))

                Column {
                    Text(
                        text = "Quick Links",
                        color = TitleGold,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                    )


                    Box(
                        modifier = Modifier
                            .width(80.dp)
                            .height(1.dp)
                            .background(DividerGold)
                    )
                    Row(Modifier.clickable(onClick = {  }),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "•",
                            color = TitleGold,
                            fontSize = 14.sp
                        )

                        Spacer(Modifier.width(8.dp))

                        Text(
                            text = "Privacy Policy",
                            color = TextGold,
                            fontSize = 8.sp
                        )
                    }
                    Row(Modifier.clickable(onClick = {  }),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "•",
                            color = TitleGold,
                            fontSize = 14.sp
                        )

                        Spacer(Modifier.width(8.dp))

                        Text(
                            text = "Terms & Conditions",
                            color = TextGold,
                            fontSize = 8.sp
                        )
                    }
                }
                Spacer(Modifier.width(8.dp))

                Column {
                    Text(
                        text = "Follow us ",
                        color = TitleGold,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                    )


                    Box(
                        modifier = Modifier
                            .width(80.dp)
                            .height(1.dp)
                            .background(DividerGold)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Image(
                            painter = painterResource(Res.drawable.youtube),
                            contentDescription = "Youtube image",
                            modifier = Modifier.size(20.dp).clickable(onClick = {  })
                        )
Spacer(Modifier.width(8.dp))
                        Image(
                            painter = painterResource(Res.drawable.facebook),
                            contentDescription = "facebook",
                            modifier = Modifier.size(20.dp).clickable(onClick = {  })
                        )
                        Spacer(Modifier.width(8.dp))
                        Image(
                            painter = painterResource(Res.drawable.insta),
                            contentDescription = "instagram",
                            modifier = Modifier.size(20.dp).clickable(onClick = {  })
                        )
                        Spacer(Modifier.width(8.dp))
                        Image(
                            painter = painterResource(Res.drawable.twitter),
                            contentDescription = "twitter",
                            modifier = Modifier.size(20.dp).clickable(onClick = {  })
                        )
                    }
                }
                Spacer(Modifier.width(12.dp))
            }

            Spacer(Modifier.weight(1f))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(Modifier.weight(1f))
                Column {
                    Text(
                        text = "© Black Pearl Reserved 2025",
                        color = FooterMuted,
                        fontSize = 12.sp,
                        letterSpacing = 0.4.sp
                    )
                    Row(Modifier.padding(start=16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = "Powered by Jrexl",
                            color = FooterGold,
                            fontSize = 8.sp,
                            letterSpacing = 0.4.sp
                        )

                        Spacer(Modifier.width(6.dp))

                        Image(
                            painter = painterResource(Res.drawable.jrexl),
                            contentDescription = "Jrexl",
                            modifier = Modifier.size(20.dp)
                        )
                    }

                }
                Spacer(Modifier.weight(1f))

            }


        }

    }

}

@Composable
fun UnsupportedDeviceScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0B1C2D)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Device Not Supported",
                color = Color(0xFFF6CF9A),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "Please use a larger screen",
                color = Color(0xFFE8D8B5),
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun RotateDeviceScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0B1C2D)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Please rotate your device",
                color = Color(0xFFF6CF9A),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "Portrait mode is required",
                color = Color(0xFFE8D8B5),
                fontSize = 12.sp
            )
        }
    }
}



@Composable
fun isLandscape(): Boolean {
    val size = LocalWindowInfo.current.containerSize
    return size.width > size.height
}

@Composable
fun isTooSmallPhone(): Boolean {
    val size = LocalWindowInfo.current.containerSize
    return size.width < 320
}



fun heightCategory(heightDp: Int): HeightCategory =
    when {
        heightDp <= 640 -> HeightCategory.H640
        heightDp <= 655 -> HeightCategory.H655
        heightDp <= 670 -> HeightCategory.H670
        heightDp <= 685 -> HeightCategory.H685
        heightDp <= 700 -> HeightCategory.H700
        heightDp <= 715 -> HeightCategory.H715
        heightDp <= 730 -> HeightCategory.H730
        heightDp <= 745 -> HeightCategory.H745
        heightDp <= 760 -> HeightCategory.H760
        heightDp <= 775 -> HeightCategory.H775
        heightDp <= 790 -> HeightCategory.H790
        heightDp <= 805 -> HeightCategory.H805
        heightDp <= 820 -> HeightCategory.H820
        else -> HeightCategory.H835
    }


@Composable
fun screenheight(): Int {
    val size = LocalWindowInfo.current.containerSize
    val density = LocalDensity.current
    return with(density) { size.height.toDp().value.toInt() }
}

@Composable
fun heightWidthRatio(): Float {
    val size = LocalWindowInfo.current.containerSize
    return size.height.toFloat() / size.width.toFloat()
}


@Composable
fun desktophomepage(){

}