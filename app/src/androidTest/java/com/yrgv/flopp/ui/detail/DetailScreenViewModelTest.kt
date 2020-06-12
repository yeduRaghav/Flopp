package com.yrgv.flopp.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.yrgv.flopp.data.repo.ListingsRepository
import com.yrgv.flopp.util.Either
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


/**
 * Test detail screen viewModel
 */
@RunWith(JUnit4::class)
class DetailScreenViewModelTest {
    @Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModelSut: DetailScreenViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        val repository = Mockito.mock(ListingsRepository::class.java)
        viewModelSut = DetailScreenViewModel(repository)
    }

    @Test
    fun handleResultFromDbErrorSetsViewStateLoadingFailed() {
        viewModelSut.handleResultFromDb(Either.Error(Unit))
        Assert.assertTrue(viewModelSut.getViewState().value == DetailScreenViewModel.ViewState.LoadingFailed)
    }


    @Test
    fun testDetailIsFetchedFromDbWhenApiFetchFails() {
        //todo:
    }

}