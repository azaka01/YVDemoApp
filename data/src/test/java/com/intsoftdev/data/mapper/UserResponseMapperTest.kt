package com.intsoftdev.data.mapper

import com.intsoftdev.data.model.UserResponseItem
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import kotlin.test.assertEquals

class UserResponseMapperTest {

    @Test
    fun `given user email when api response then correct email used`() {
        val responseItem = mockk<UserResponseItem>(relaxed = true)

        val userEmail = "email.com"
        every {
            responseItem.emailAddress
        } returns userEmail


        val test = UserResponseMapper.responseToUserDetails(responseItem)

        assertEquals(expected = userEmail, actual = test.email)
    }

    @Test
    fun `given user name when api response then correct name used`() {

        val userFirstName = "Joe"
        val userLastName = "Bloggs"

        val responseItem = mockk<UserResponseItem>(relaxed = true) {
            every { name.firstName } returns userFirstName
            every { name.lastName } returns userLastName
        }

        val test = UserResponseMapper.responseToUserDetails(responseItem)

        assertEquals(expected = (userFirstName + " " + userLastName), actual = test.displayName)
    }

    @Test
    fun `given user address when api response then location used`() {

        val userHouse = "76"
        val userStreet = "Any Street"
        val userCity = "London"
        val userCountry = "UK"
        val responseItem = mockk<UserResponseItem>(relaxed = true) {
            every { location.street.houseNumber } returns userHouse
            every { location.street.streetName } returns userStreet
            every { location.city } returns userCity
            every { location.country } returns userCountry
        }

        val test = UserResponseMapper.responseToUserDetails(responseItem)

        assertEquals(
            expected = ("$userHouse $userStreet $userCity $userCountry"),
            actual = test.displayAddress
        )
    }
}