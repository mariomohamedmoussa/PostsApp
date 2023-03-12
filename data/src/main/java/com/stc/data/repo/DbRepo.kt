package com.stc.data.repo

import com.stc.data.locale.CategoryDao
import com.stc.domain.entity.Category
import javax.inject.Inject

class DbRepo @Inject constructor(private val dao: CategoryDao) {
    fun saveCategory (category: Category)= dao.insertCategory(category)
    fun updateCategory (category: Category)= dao.updateCategory(category)
    fun deleteCategory (category: Category)= dao.deleteCategory(category)
    fun getAllCategory ()= dao.getAllCategory()
}