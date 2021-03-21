package com.intsoftdev.yvdemoapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.intsoftdev.domain.GetUsersUseCase
import com.intsoftdev.domain.UserDetails
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UsersViewModelTest {

    @Rule
    @JvmField
    val testSchedulerRule = TestSchedulerRule()

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val getUsersUseCase: GetUsersUseCase = mock()

    private lateinit var usersViewModel: UsersViewModel

    @Before
    fun setUp() {
        usersViewModel = UsersViewModel(getUsersUseCase)
    }

    @Test
    fun `given use case when api returns success then result expected`() {

        // given
        val successResult = ResultState.Success(
            getTestList()
        )
        whenever(getUsersUseCase.getUsers(any())).thenReturn(Single.just(getTestList()))

        // when
        usersViewModel.getAllUsers()


        // then
        assertEquals(successResult, usersViewModel.getLiveData().value)
    }

    @Test
    fun `given use case when api returns error then error handled`() {
        // given
        val throwable = Throwable("Runtime exception")
        val error = ResultState.Failure(throwable)

        whenever(getUsersUseCase.getUsers(any())).thenReturn(Single.error(throwable))

        // when
        usersViewModel.getAllUsers()

        // then
        assertEquals(error, usersViewModel.getLiveData().value)
    }

    private fun getTestList() = listOf(testUser1, testUser2)

    companion object {
        private val testUser1 = UserDetails(
            displayName = "user1",
            displayAddress = "home1",
            email = "user1@mail.com",
            latitude = 51.5,
            longitude = 0.0,
            picUrl = "url1"
        )

        private val testUser2 = UserDetails(
            displayName = "user2",
            displayAddress = "home2",
            email = "user2@mail.com",
            latitude = 7.0,
            longitude = 45.0,
            picUrl = "url2"
        )
    }

}