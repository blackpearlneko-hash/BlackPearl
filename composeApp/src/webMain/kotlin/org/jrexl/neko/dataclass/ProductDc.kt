package org.jrexl.neko.dataclass

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Productdc(

    @SerialName("Prodtname")
    val name: String,

    @SerialName("Prodtprice")
    val price: Double,

    @SerialName("Productimg")   // ✅ MUST MATCH BACKEND
    val images: List<String> = emptyList()
)
