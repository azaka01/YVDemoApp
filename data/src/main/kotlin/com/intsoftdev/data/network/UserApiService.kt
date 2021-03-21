package com.intsoftdev.data.network

import com.intsoftdev.data.model.UserApiResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

internal interface UserApiService {
    @GET("api/")
    fun getUsers(
        @Query("results") numResults: Long,
        @Query("seed") seed: String
    ): Single<UserApiResponse>
}