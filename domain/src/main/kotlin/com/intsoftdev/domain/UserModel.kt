package com.intsoftdev.domain

data class UserDetails(
    val displayName: String,
    val picUrl: String,
    val email: String,
    val displayAddress: String,
    val latitude: Double,
    val longitude: Double
)
