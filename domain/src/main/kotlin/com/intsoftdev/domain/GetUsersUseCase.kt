package com.intsoftdev.domain

import javax.inject.Inject

class GetUsersUseCase @Inject constructor(private val userRepository: UserRepository) {
    fun getUsers(numUsers: Long) = userRepository.getUsers(numResults = numUsers)
}