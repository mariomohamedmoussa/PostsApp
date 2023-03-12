package com.stc.data.locale

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.Update
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.stc.domain.entity.Category
import com.stc.domain.utils.ConstantDomain.CATEGORY_TABLE
import io.reactivex.Flowable

@Dao
 interface CategoryDao {

    @Query("SELECT * FROM $CATEGORY_TABLE")
     fun getAllCategory(): Flowable<List<Category>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertCategory(category: Category)

    @Update
    fun updateCategory(category: Category)

    @Delete
    fun deleteCategory(category: Category)


}