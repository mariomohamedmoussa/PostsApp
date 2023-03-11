package com.stc.data.remote

import com.stc.domain.entity.CategoriesResponse
import retrofit2.http.GET

interface ApiService {
    @GET("categories.php")
   suspend fun getCategories():CategoriesResponse
}