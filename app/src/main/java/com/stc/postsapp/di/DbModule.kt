package com.stc.postsapp.di

import android.content.Context
import androidx.room.Room
import com.stc.data.locale.CategoryDataBase
import com.stc.domain.entity.Category
import com.stc.domain.utils.ConstantDomain.CATEGORY_TABLE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, CategoryDataBase::class.java, CATEGORY_TABLE
    )
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideDao(db:CategoryDataBase) = db.getCategoryDao()

    @Provides
    @Singleton
    fun provideEntity() = Category()
}