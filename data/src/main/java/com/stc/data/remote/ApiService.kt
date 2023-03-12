package com.stc.data.remote

import com.stc.domain.entity.CategoriesResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getCategories(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): CategoriesResponse


    @POST("/movie/{movie_id}")
    @FormUrlEncoded
    suspend fun getCategoryDetails(
        @Path("movie_id") movieId: String,
        @Field("api_key") apiKey: String
    ): CategoriesResponse
}