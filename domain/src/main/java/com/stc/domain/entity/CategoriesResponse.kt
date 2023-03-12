package com.stc.domain.entity

import com.google.gson.annotations.SerializedName


data class CategoriesResponse( @SerializedName("page")  var page: Int?,
                               @SerializedName("results") var category: List<Category>,
                               @SerializedName("total_pages") var totalPages: Int?,
                               @SerializedName("total_results") var totalResults: Int? )

