package org.jrexl.neko.dataclass

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Productdc(

    @SerialName("Prodtname")
    val name: String,

    @SerialName("Prodtdescription")
    val description: String,

    @SerialName("Prodtprice")
    val price: Double, // Change to String if your backend sends it as a string

    @SerialName("Prodtcategory")
    val category: String,

    @SerialName("ProdtstockQuantity")
    val stockQuantity: Int,

    // Your backend sends an array of URL strings
    @SerialName("Productimg")
    val images: List<String>
)