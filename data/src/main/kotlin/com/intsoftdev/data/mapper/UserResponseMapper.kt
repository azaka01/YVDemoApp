package com.intsoftdev.data.mapper

import com.intsoftdev.data.model.UserResponseItem
import com.intsoftdev.domain.UserDetails
import io.reactivex.Single

internal object UserResponseMapper {

    fun responseToUserDetails(userApiResponse: UserResponseItem): UserDetails =
        UserDetails(
            displayName = userApiResponse.name.firstName + " " + userApiResponse.name.lastName,
            picUrl = userApiResponse.picture.medium,
            email = userApiResponse.emailAddress,
            displayAddress = getStreetAddress(userApiResponse),
            latitude = userApiResponse.location.coordinates.latitude,
            longitude = userApiResponse.location.coordinates.longitude,
        )

    private fun getStreetAddress(userApiResponse: UserResponseItem): String =
        userApiResponse.location.street.houseNumber + " "  + userApiResponse.location.street.streetName + " " + userApiResponse.location.city + " " + userApiResponse.location.country
}

fun <T, R> Single<List<T>>.mapValues(transform: (T) -> R): Single<List<R>> =
    map { it.map(transform) }