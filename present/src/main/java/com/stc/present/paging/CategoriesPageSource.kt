package com.stc.present.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.stc.domain.entity.Category
import com.stc.domain.usecase.CategoriesUseCase
import com.stc.domain.utils.ConstantDomain
import java.net.HttpRetryException

class CategoriesPageSource (private val categoriesUseCase: CategoriesUseCase) :
    PagingSource<Int, Category>() {
    override fun getRefreshKey(state: PagingState<Int, Category>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Category> {
        return try {
            val currPage = params.key ?: 1
            val response = categoriesUseCase.getFromRemote(currPage)

            val data = response.category
            val responseData = mutableListOf<Category>()
            data.map {
                it.posterPath = ConstantDomain.POSTER_BASE_URL+it.posterPath
            }

            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey = if (currPage == 1) null else -1,
                nextKey = currPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        } catch (httpE: HttpRetryException) {

            return LoadResult.Error(httpE)
        }
    }
}