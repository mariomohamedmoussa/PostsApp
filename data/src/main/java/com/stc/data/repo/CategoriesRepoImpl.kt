package com.stc.data.repo

import com.stc.data.remote.ApiService
import com.stc.domain.entity.CategoriesResponse
import com.stc.domain.repo.CategoriesRepo

class CategoriesRepoImpl (private val apiService: ApiService):CategoriesRepo {
    override suspend fun getCategoriesFromRemote(): CategoriesResponse  = apiService.getCategories()
}