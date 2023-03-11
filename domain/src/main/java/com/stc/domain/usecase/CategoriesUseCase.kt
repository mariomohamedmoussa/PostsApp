package com.stc.domain.usecase

import com.stc.domain.repo.CategoriesRepo

class CategoriesUseCase (private val categoriesRepo: CategoriesRepo){
    suspend operator fun invoke() = categoriesRepo.getCategoriesFromRemote()
}