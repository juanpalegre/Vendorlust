package com.example.vendorlust.data.model
import com.google.gson.annotations.SerializedName

data class Vendor(
    @SerializedName("id") val id: Int,
    @SerializedName("display_name") val displayName: String,
    @SerializedName("name") val name: String,
    @SerializedName("timezone") val timezone: String,
    @SerializedName("description") val description: String,
    @SerializedName("contact_info") val contactInfo: ContactInfo,
    @SerializedName("gallery") val gallery: List<Photo>,
    @SerializedName("opening_hours") val openingHours: OpeningHours,
    @SerializedName("hero_image") val heroImage: Image
)

data class VendorList(
    val results: List<Vendor> = listOf()
)

data class ContactInfo(
    @SerializedName("phone_number") val phoneNumber: String,
    @SerializedName("email_address") val emailAddress: String,
    @SerializedName("website_url") val websiteUrl: String,
    @SerializedName("location") val location: Location,
    @SerializedName("address") val address: Address
)

data class Location(
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double
)

data class Address(
    @SerializedName("address_line_1") val addressLine1: String,
    @SerializedName("address_line_2") val addressLine2: String?,
    @SerializedName("city") val city: String,
    @SerializedName("state") val state: String?,
    @SerializedName("postal_code") val postalCode: String,
    @SerializedName("country") val country: String,
    @SerializedName("region") val region: String?
)

data class OpeningHours(
    @SerializedName("monday") val monday: List<OpeningTime>,
    @SerializedName("tuesday") val tuesday: List<OpeningTime>,
    @SerializedName("wednesday") val wednesday: List<OpeningTime>,
    @SerializedName("thursday") val thursday: List<OpeningTime>,
    @SerializedName("friday") val friday: List<OpeningTime>,
    @SerializedName("saturday") val saturday: List<OpeningTime>,
    @SerializedName("sunday") val sunday: List<OpeningTime>
)

data class OpeningTime(
    @SerializedName("id") val id: Int,
    @SerializedName("opens_at") val opensAt: String,
    @SerializedName("closes_at") val closesAt: String,
    @SerializedName("closes_late") val closesLate: Boolean
)

data class Photo(
    @SerializedName("id") val id: Int,
    @SerializedName("image") val image: Image
)

data class Image(
    @SerializedName("id") val id: Int,
    @SerializedName("url") val url: String
)


