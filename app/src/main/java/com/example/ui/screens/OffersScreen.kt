package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material.icons.filled.QrCode2
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.MenuRepository
import com.example.data.Offer
import com.example.ui.theme.BlushBackground
import com.example.ui.theme.BlushBorder
import com.example.ui.theme.BlushSurface
import com.example.ui.theme.EspressoMuted
import com.example.ui.theme.EspressoText
import com.example.ui.theme.PlayfairFontFamily
import com.example.ui.theme.PlusJakartaFontFamily
import com.example.ui.theme.RoseLight
import com.example.ui.theme.RosePrimary

@Composable
fun OffersScreen(
    onOfferSelected: (Offer) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedOfferForDialog by remember { mutableStateOf<Offer?>(null) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(BlushBackground)
            .padding(16.dp)
            .testTag("offers_screen"),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Special Offers",
                    fontFamily = PlayfairFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = EspressoText
                )
                Text(
                    text = "Exclusive treats and daily cafe deals crafted for you.",
                    fontFamily = PlusJakartaFontFamily,
                    fontSize = 13.sp,
                    color = EspressoMuted,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }
        }

        items(MenuRepository.sampleOffers, key = { it.id }) { offer ->
            OfferCard(
                offer = offer,
                onShowCodeClick = { selectedOfferForDialog = offer }
            )
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }

    // Modal Dialog showing QR / Counter redemption details
    selectedOfferForDialog?.let { offer ->
        AlertDialog(
            onDismissRequest = { selectedOfferForDialog = null },
            shape = RoundedCornerShape(20.dp),
            containerColor = BlushSurface,
            title = {
                Text(
                    text = offer.title,
                    fontFamily = PlayfairFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = EspressoText
                )
            },
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(70.dp)
                            .clip(CircleShape)
                            .background(RoseLight),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.QrCode2,
                            contentDescription = null,
                            tint = RosePrimary,
                            modifier = Modifier.size(44.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Promo Code: ${offer.code}",
                        fontFamily = PlusJakartaFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = RosePrimary
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = offer.description,
                        fontFamily = PlusJakartaFontFamily,
                        fontSize = 12.sp,
                        color = EspressoMuted,
                        lineHeight = 16.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Validity: ${offer.validity}",
                        fontFamily = PlusJakartaFontFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 11.sp,
                        color = EspressoText
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = { selectedOfferForDialog = null },
                    colors = ButtonDefaults.buttonColors(containerColor = RosePrimary),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "Done",
                        fontFamily = PlusJakartaFontFamily,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        )
    }
}

@Composable
fun OfferCard(
    offer: Offer,
    onShowCodeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = BlushSurface),
        border = BorderStroke(1.dp, BlushBorder),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
            .fillMaxWidth()
            .testTag("offer_card_${offer.id}")
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Header with Gradient
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(Color(0xFFE05374), Color(0xFFC73859))
                        )
                    )
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = when (offer.iconType) {
                                "coffee" -> Icons.Default.Coffee
                                "cake" -> Icons.Default.Cake
                                "tea" -> Icons.Default.Coffee
                                else -> Icons.Default.Stars
                            },
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = offer.discount,
                            fontFamily = PlusJakartaFontFamily,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 13.sp,
                            color = Color.White
                        )
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White.copy(alpha = 0.25f))
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = offer.code,
                            fontFamily = PlusJakartaFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp,
                            color = Color.White
                        )
                    }
                }
            }

            // Body
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = offer.title,
                    fontFamily = PlusJakartaFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = EspressoText
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = offer.description,
                    fontFamily = PlusJakartaFontFamily,
                    fontSize = 12.sp,
                    lineHeight = 17.sp,
                    color = EspressoMuted
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.AccessTime,
                            contentDescription = null,
                            tint = EspressoMuted,
                            modifier = Modifier.size(13.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = offer.validity,
                            fontFamily = PlusJakartaFontFamily,
                            fontSize = 11.sp,
                            color = EspressoMuted
                        )
                    }

                    Surface(
                        onClick = onShowCodeClick,
                        shape = RoundedCornerShape(16.dp),
                        color = RoseLight,
                        modifier = Modifier.height(32.dp)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.padding(horizontal = 12.dp)
                        ) {
                            Text(
                                text = "Show at Counter",
                                fontFamily = PlusJakartaFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp,
                                color = RosePrimary
                            )
                        }
                    }
                }
            }
        }
    }
}
