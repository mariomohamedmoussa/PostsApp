package com.stc.domain.usecase

import com.stc.domain.entity.CategoriesResponse
import com.stc.domain.entity.Category
import com.stc.domain.repo.CategoriesRepo
import io.reactivex.Flowable

class CategoriesUseCase (private val categoriesRepo: CategoriesRepo){
    suspend  fun getFromRemote(page:Int) :CategoriesResponse{
         return categoriesRepo.getCategoriesFromRemote(page)
    }
    suspend  fun getFromLocale() : Flowable<List<Category>> {
         return categoriesRepo.getCategoriesFromLocale()
    }
}