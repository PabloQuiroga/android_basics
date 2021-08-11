package com.siar.democoroutines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(val ioDispatcher: CoroutineDispatcher = Dispatchers.IO): ViewModel() {

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> get() = _loginResult

    /*
    el nombre del metodo es dado porque
    Nadie debe decirle al viewModel lo que debe hacer sino lo que ha ocurrido
     */
    fun onLoginClick(user: String, pass: String) {
        viewModelScope.launch {
            _loginResult.value = withContext(ioDispatcher){
                validateLogin(user, pass)
            }
        }

    }

    private fun validateLogin(user: String, pass: String): Boolean {
        Thread.sleep(5000) // Mock para coroutines
        return user.isNotEmpty() && pass.isNotEmpty()
    }
}