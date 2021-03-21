package com.intsoftdev.domain

import io.reactivex.Single

interface UserRepository {
    fun getUsers(numResults: Long) : Single<List<UserDetails>>
}