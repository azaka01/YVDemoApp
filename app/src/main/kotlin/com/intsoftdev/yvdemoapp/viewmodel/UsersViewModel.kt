package com.intsoftdev.yvdemoapp.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.intsoftdev.domain.GetUsersUseCase
import com.intsoftdev.domain.UserDetails
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

class UsersViewModel @ViewModelInject constructor(
    private val usersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _viewStateLiveData = MutableLiveData<ResultState<List<UserDetails>>>()
    fun getLiveData(): LiveData<ResultState<List<UserDetails>>> = _viewStateLiveData

    private val compositeDisposable = CompositeDisposable()

    internal var selectedUser: UserDetails? = null

    fun getAllUsers() {
        compositeDisposable.clear()

        usersUseCase.getUsers(NUM_USERS)
            .doOnSubscribe {
                _viewStateLiveData.postValue(
                    ResultState.Busy
                )
            }
            .subscribe({ usersList ->
                Timber.d("got users ${usersList.size}")
                _viewStateLiveData.postValue(
                    ResultState.Success(usersList)
                )
            }, {
                Timber.e(it)
                _viewStateLiveData.postValue(
                    ResultState.Failure(it)
                )
            }

            ).also {
                compositeDisposable.add(it)
            }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    companion object {
        private const val NUM_USERS = 500L
    }
}