package com.intsoftdev.data.repository

import com.intsoftdev.data.mapper.UserResponseMapper
import com.intsoftdev.data.mapper.mapValues
import com.intsoftdev.data.network.UserApiService
import com.intsoftdev.domain.UserDetails
import com.intsoftdev.domain.UserRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

internal class UserRepositoryImpl @Inject constructor(private val userApiService: UserApiService) :
    UserRepository {

    override fun getUsers(numResults: Long): Single<List<UserDetails>> =
        userApiService.getUsers(numResults = numResults, seed = YV_SEED)
            .map { apiResponse ->
                apiResponse.users
            }.mapValues { userResponseItem ->
                UserResponseMapper.responseToUserDetails(userResponseItem)
            }.subscribeOn(Schedulers.io())

    companion object {
        private const val YV_SEED = "yv"
    }
}