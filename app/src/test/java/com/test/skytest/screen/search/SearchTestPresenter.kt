package com.test.skytest.screen.search

import com.test.skytest.data.network.api.search.response.Meaning
import com.test.skytest.data.network.api.search.response.Translation
import com.test.skytest.data.network.api.search.response.Word
import com.test.skytest.data.repository.WordsRepository
import io.mockk.mockk
import io.mockk.mockkClass
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.observers.TestObserver
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SearchTestPresenter {
    @Mock
    private lateinit var view: SearchView
  @Mock
    private lateinit var viewState: `SearchView$$State`


    @Mock
    private lateinit var wordsRepository: WordsRepository
    private val scheduler = TestScheduler()
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

    @Before
    fun setup() {
        RxJavaPlugins.setIoSchedulerHandler { scheduler }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler {
            scheduler
        }
    }



    @Test
    fun clickOnWordShowsMeaningsScreen() {
        val presenter = SearchPresenter(wordsRepository)
presenter.attachView(view)

        presenter.onWordClick(word)

        Mockito.verify(view).showMeanings(longArrayOf(1,2))
    }

    @After
    fun reset() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }

}