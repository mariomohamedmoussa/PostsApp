package com.stc.postsapp.di

import com.stc.domain.repo.CategoriesRepo
import com.stc.domain.usecase.CategoriesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideUseCase(categoriesRepo: CategoriesRepo):CategoriesUseCase{
        return CategoriesUseCase(categoriesRepo)
    }
}