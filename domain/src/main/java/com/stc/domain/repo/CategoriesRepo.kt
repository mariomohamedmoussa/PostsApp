package com.stc.domain.repo

import com.stc.domain.entity.CategoriesResponse

interface CategoriesRepo {
   suspend fun getCategoriesFromRemote ():CategoriesResponse
}