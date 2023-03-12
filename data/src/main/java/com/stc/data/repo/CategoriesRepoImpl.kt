package com.stc.data.repo


import com.stc.data.remote.ApiService
import com.stc.domain.entity.CategoriesResponse
import com.stc.domain.entity.Category
import com.stc.domain.repo.CategoriesRepo
import com.stc.domain.utils.ConstantDomain.API_KEY
import io.reactivex.Flowable

class CategoriesRepoImpl (private val apiService: ApiService , private val dao: DbRepo):CategoriesRepo {
    override suspend fun getCategoriesFromRemote(page:Int): CategoriesResponse {
     return   apiService.getCategories(API_KEY, page)
    }

    override suspend fun getCategoriesFromLocale(): Flowable<List<Category>> {
        return   dao.getAllCategory()
    }
}