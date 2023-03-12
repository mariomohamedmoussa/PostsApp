package com.stc.domain.repo

import com.stc.domain.entity.CategoriesResponse
import com.stc.domain.entity.Category
import io.reactivex.Flowable

interface CategoriesRepo {
   suspend fun getCategoriesFromRemote (page:Int):CategoriesResponse
   suspend fun getCategoriesFromLocale (): Flowable<List<Category>>
}