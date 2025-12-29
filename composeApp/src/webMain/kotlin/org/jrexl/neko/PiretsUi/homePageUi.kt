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


@Composable
fun homePageUi() {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        val screenWidth = maxWidth
        val isMobile = screenWidth < 750.dp
        if (isMobile) {
            mobilehomepage()
        }
        else{
            desktophomepage()
        }
    }

}

@Composable
fun mobilehomepage(){

    val offsetX = remember { Animatable(0f) }
    val leftLimit = -200f
    val rightLimit = 200f
    val BlackPearlGold = Color(0xFFF6CF9A)
    val TitleGold = Color(0xFFF6CF9A)
    val TextGold = Color(0xFFE8D8B5)
    val DividerGold = Color(0xFF8A6B3E)
    val FooterGold = Color(0xFFF6CF9A)
    val FooterMuted = Color(0xFFE8D8B5)






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

    Box(Modifier.fillMaxWidth().fillMaxHeight()) {
        Image(
            painter = painterResource(Res.drawable.mobilehome),
            contentDescription = "best",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxHeight().fillMaxWidth()
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


        Column(Modifier.fillMaxWidth().fillMaxHeight()) {
        Text(
            text = "Black Pearl, Where Every Piece Has Sailed Through Time",
            color = Color(0xFFE6D3A3),
            fontSize = 10.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(top = 52.dp)
                .offset { IntOffset(offsetX.value.toInt(), 0) }
        )
            Spacer(Modifier.height(7.dp))
            Image(
                painter = painterResource(Res.drawable.logolo),
                contentDescription = "logo",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(110.dp).padding(start = 10.dp)
            )
            Spacer(Modifier.height(5.dp))

            Row(
                modifier = Modifier
                    .clickable {  }
                    .padding(end = 37.dp),
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
                Spacer(modifier = Modifier.width(6.dp))
            }

            Spacer(Modifier.height(77.dp))
            Row {
                Text(
                    text = "Explore",
                    modifier = Modifier.padding(start = 50.dp).clickable(onClick = {  }),
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

            Spacer(Modifier.height(6.dp))
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

            Spacer(Modifier.height(75.dp))

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
fun desktophomepage(){

}