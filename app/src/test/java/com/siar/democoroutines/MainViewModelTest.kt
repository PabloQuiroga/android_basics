package com.siar.democoroutines

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    /*
    Para poder testear los liveData añadimos una regla
    Obliga a los liveData a ejecutarse en el hilo del test
        en vez del hilo principal
     */
    @get: Rule
    val rule = InstantTaskExecutorRule()


    @get: Rule
    val coroutinesTestRule = CoroutinesTestRule()


    private lateinit var viewModel: MainViewModel

    @Before
    fun setup(){
        viewModel = MainViewModel(coroutinesTestRule.testDispatcher)
    }

    @Test
    fun success_data_not_empty(){
        val observer = mock<Observer<Boolean>>()
        coroutinesTestRule.testDispatcher.runBlockingTest {

            //observa el liveData durante el tiempo que exista
            viewModel.loginResult.observeForever(observer)

            viewModel.onLoginClick("user", "pass")

            verify(observer).onChanged(true)
        }

    }
}