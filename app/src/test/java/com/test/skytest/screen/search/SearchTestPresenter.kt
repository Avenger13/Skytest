package com.test.skytest.screen.search

import com.test.skytest.data.network.api.search.response.Meaning
import com.test.skytest.data.network.api.search.response.Translation
import com.test.skytest.data.network.api.search.response.Word
import com.test.skytest.data.repository.WordsRepositoryImpl
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.TimeUnit

@RunWith(MockitoJUnitRunner::class)
class SearchTestPresenter {
    @Mock
    private lateinit var view: SearchView

    @Mock
    private lateinit var wordsRepository: WordsRepositoryImpl
    private val scheduler = TestScheduler()
    private lateinit var presenter: SearchPresenter
    val word = Word(
        10, "text", listOf(
            Meaning(
                1,
                "",
                Translation("", ""),
                "",
                "",
                "",
                ""
            ),
            Meaning(
                2,
                "",
                Translation("", ""),
                "",
                "",
                "",
                ""
            )
        )
    )

    val words = listOf(
        Word(1, "t1", emptyList()),
        Word(2, "t2", emptyList()),
    )

    @Before
    fun setup() {
        RxJavaPlugins.setIoSchedulerHandler { scheduler }
        RxJavaPlugins.setComputationSchedulerHandler { scheduler }
        RxJavaPlugins.setNewThreadSchedulerHandler { scheduler }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler }
        MockitoAnnotations.openMocks(this)
        presenter = SearchPresenter(wordsRepository)
        presenter.attachView(view)
        scheduler.triggerActions()
    }


    @Test
    fun clickOnWord_showsMeaningsScreen() {

        presenter.onWordClick(word)
        Mockito.verify(view).showMeanings(longArrayOf(1, 2))
    }


    @Test
    fun onSearch_withNullQuery_showsEmptyList() {

        presenter.onSearch(null)

        Mockito.verify(view).showSearchResults(emptyList())
    }


    @Test
    fun onSearch_withNotNullQuery_showsLoadingAndResults() {
        val query = "some query"
        Mockito.`when`(wordsRepository.search(query)).thenReturn(Single.just(words))

        presenter.onSearch(query)
        scheduler.triggerActions()
        scheduler.advanceTimeBy(5, TimeUnit.SECONDS)

        Mockito.verify(view, Mockito.times(1)).showProgress(true)
        Mockito.verify(view, Mockito.times(1)).showProgress(false)
        Mockito.verify(view).showSearchResults(words)
    }


    @After
    fun reset() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }


}