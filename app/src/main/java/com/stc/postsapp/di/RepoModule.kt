package com.stc.postsapp.di

import com.stc.data.remote.ApiService
import com.stc.data.repo.CategoriesRepoImpl
import com.stc.domain.repo.CategoriesRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    fun providesRepo(apiService: ApiService):CategoriesRepo{
        return CategoriesRepoImpl(apiService)
    }
}