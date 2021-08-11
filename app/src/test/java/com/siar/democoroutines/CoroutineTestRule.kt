package com.siar.democoroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@ExperimentalCoroutinesApi
class CoroutinesTestRule: TestWatcher() {

    val testDispatcher = TestCoroutineDispatcher() //Dispatcher propio para corroutines en testing

    // similar a @Before en junit
    override fun starting(description: Description?) {
        super.starting(description)

        Dispatchers.setMain(testDispatcher)
    }

    // similar a @After en junit
    override fun finished(description: Description?) {
        super.finished(description)

        testDispatcher.cleanupTestCoroutines()
        Dispatchers.resetMain()
    }
}