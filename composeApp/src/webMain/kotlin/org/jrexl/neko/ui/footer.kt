package org.jrexl.neko.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import blackpearl.composeapp.generated.resources.Res
import blackpearl.composeapp.generated.resources.jrexl
import org.jetbrains.compose.resources.painterResource

@Composable
fun FooterSection() {
    // --- Theme Colors ---
    val deepNavy = Color(0xFF0A192F)
    val goldAccent = Color(0xFFC5A059)
    val softWhite = Color(0xFFE0E0E0)
    val mutedGrey = Color(0xFFB0B0B0)

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .background(deepNavy)
            .padding(top = 64.dp, bottom = 24.dp)
    ) {
        val isMobile = maxWidth < 800.dp

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // --- MAIN CONTENT AREA ---
            if (isMobile) {
                // Mobile Layout: Stacked
                FooterNewsletter(goldAccent, softWhite)
                Spacer(modifier = Modifier.height(48.dp))
                FooterLinks(goldAccent, softWhite, Alignment.CenterHorizontally)
                Spacer(modifier = Modifier.height(48.dp))
                FooterSeoText(mutedGrey, Alignment.CenterHorizontally)
            } else {
                // Desktop Layout: 3 Columns
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    // Col 1: SEO Text (30% width)
                    Box(modifier = Modifier.weight(1f)) {
                        FooterSeoText(mutedGrey, Alignment.Start)
                    }

                    // Col 2: Links (30% width)
                    Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.TopCenter) {
                        FooterLinks(goldAccent, softWhite, Alignment.CenterHorizontally)
                    }

                    // Col 3: Newsletter (30% width)
                    Box(modifier = Modifier.weight(1f)) {
                        FooterNewsletter(goldAccent, softWhite)
                    }
                }
            }

            // --- DIVIDER ---
            Spacer(modifier = Modifier.height(64.dp))
            HorizontalDivider(color = mutedGrey.copy(alpha = 0.2f))
            Spacer(modifier = Modifier.height(24.dp))

            // --- BOTTOM BAR: COPYRIGHT & POWERED BY ---
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "© 2025 Black Pearl. All rights reserved.",
                    color = mutedGrey,
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                // POWERED BY BLOCK
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.alpha(0.7f)
                ) {
                    Text(
                        text = "Powered by jrexl",
                        color = mutedGrey,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Image(
                        painterResource(Res.drawable.jrexl),
                        contentDescription = "jrexl",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

// --- SUB-COMPONENTS ---

@Composable
fun FooterLinks(headerColor: Color, textColor: Color, alignment: Alignment.Horizontal) {
    Column(horizontalAlignment = alignment) {
        // Header
        Text(
            text = "QUICK LINKS",
            color = headerColor,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            letterSpacing = 1.sp
        )
        Spacer(modifier = Modifier.height(24.dp))

        val links = listOf(
            "Home", "Shop Brass Handicrafts", "Nautical Decor",
            "About Us", "Blog", "Contact"
        )

        // Split the list into 2 columns (chunks of 3)
        val columns = links.chunked(3)

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.wrapContentWidth()
        ) {
            columns.forEachIndexed { index, columnLinks ->

                // Inner Column for the chunk of links
                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    columnLinks.forEach { link ->
                        Text(
                            text = link,
                            color = textColor,
                            fontSize = 14.sp,
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                                .clickable { /* TODO: Navigation */ } // Makes each text interactive
                        )
                    }
                }

                // Add spacing between the two columns
                if (index < columns.size - 1) {
                    Spacer(modifier = Modifier.width(48.dp))
                }
            }
        }
    }
}

@Composable
fun FooterSeoText(textColor: Color, alignment: Alignment.Horizontal) {
    Column(horizontalAlignment = alignment) {
        Text(
            text = "BLACK PEARL",
            color = Color.White,
            fontFamily = MaterialTheme.typography.headlineMedium.fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            letterSpacing = 2.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Explore premium brass handicrafts, antique nautical instruments, vintage telescopes, and ship wheels for beach-inspired decor.",
            color = textColor,
            fontSize = 13.sp,
            lineHeight = 20.sp,
            textAlign = if (alignment == Alignment.CenterHorizontally) TextAlign.Center else TextAlign.Start
        )
    }
}

@Composable
fun FooterNewsletter(accentColor: Color, textColor: Color) {
    val emailText = remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.Start) {
        Text(
            text = "Sail into Savings",
            color = textColor,
            fontSize = 18.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = "Sign up for exclusive deals.",
            color = textColor.copy(alpha = 0.7f),
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = emailText.value,
            onValueChange = { emailText.value = it },
            placeholder = { Text("Enter your email", color = Color.Gray, fontSize = 14.sp) },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = accentColor,
                unfocusedBorderColor = Color.Gray,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                cursorColor = accentColor
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(containerColor = accentColor),
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier.fillMaxWidth().height(48.dp)
        ) {
            Text(
                text = "SUBSCRIBE",
                color = Color(0xFF0A192F),
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
        }
    }
}

fun Modifier.alpha(alpha: Float) = this.then(Modifier.graphicsLayer(alpha = alpha))