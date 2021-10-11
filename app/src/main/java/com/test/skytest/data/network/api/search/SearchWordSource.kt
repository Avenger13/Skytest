package com.test.skytest.data.network.api.search

import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.test.skytest.data.network.api.search.response.Word
import com.test.skytest.data.repository.WordsRepositoryImpl
import com.test.skytest.domain.repository.WordsRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers


class SearchWordSource(
    private val wordsRepository: WordsRepository,
    private val query: String?
) : RxPagingSource<Int, Word>() {

    companion object {
        const val FIRST_KEY = 1
    }

    override fun getRefreshKey(state: PagingState<Int, Word>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Word>> {
        if (query.isNullOrBlank()) {
            return Single.just(LoadResult.Page(emptyList(), null, null))
        }

        val page = params.key ?: FIRST_KEY
        val pageSize = params.loadSize.coerceAtMost(WordsRepositoryImpl.MAX_PAGE_SIZE)

        return wordsRepository.search(query, page, pageSize)
            .subscribeOn(Schedulers.io())
            .map<LoadResult<Int, Word>> {
                val prevKey = if (page == FIRST_KEY) null else page - 1
                val nextKey = if (it.size < pageSize) null else page + 1
                LoadResult.Page(
                    data = it,
                    prevKey = prevKey,
                    nextKey = nextKey
                )
            }
            .onErrorReturn { LoadResult.Error(it) }
    }
}