package com.stc.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.stc.domain.utils.ConstantDomain.CATEGORY_DATABASE


@Entity(tableName = CATEGORY_DATABASE )
data class Category(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id") var id: Int=0,
    @SerializedName("poster_path") var posterPath: String="",
    @SerializedName("title") var title: String=""
)