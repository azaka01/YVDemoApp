package com.intsoftdev.data.model

import com.google.gson.annotations.SerializedName

internal data class UserApiResponse(
    @SerializedName("results")
    val users: List<UserResponseItem>
)

internal data class UserResponseItem(
    @SerializedName("name")
    val name: NameResponseInners,

    @SerializedName("location")
    val location: LocationResponseInners,

    @SerializedName("email")
    val emailAddress: String,

    @SerializedName("picture")
    val picture: PictureResponseInners
) {

    data class NameResponseInners(
        @SerializedName("title")
        val title: String,

        @SerializedName("first")
        val firstName: String,

        @SerializedName("last")
        val lastName: String
    )

    data class LocationResponseInners(
        @SerializedName("street")
        val street: StreetInners,

        @SerializedName("city")
        val city: String,

        @SerializedName("state")
        val state: String,

        @SerializedName("country")
        val country: String,

        @SerializedName("postcode")
        val postCode: String,

        @SerializedName("coordinates")
        val coordinates: CoordinateInners
    )

    data class StreetInners(
        @SerializedName("number")
        val houseNumber: String,

        @SerializedName("name")
        val streetName: String
    )

    data class CoordinateInners(
        @SerializedName("latitude")
        val latitude: Double,

        @SerializedName("longitude")
        val longitude: Double
    )

    data class PictureResponseInners(
        @SerializedName("large")
        val large: String,

        @SerializedName("medium")
        val medium: String,

        @SerializedName("thumbnail")
        val thumbnail: String
    )
}