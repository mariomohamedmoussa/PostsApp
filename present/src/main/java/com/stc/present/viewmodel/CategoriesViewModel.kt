package com.stc.present.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.stc.domain.entity.CategoriesResponse
import com.stc.domain.usecase.CategoriesUseCase
import com.stc.present.paging.CategoriesPageSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(private val getCategoriesUseCase: CategoriesUseCase): ViewModel() {
    private val TAG = "CategoriesViewModel"
    private val _categories: MutableStateFlow<CategoriesResponse?> = MutableStateFlow(null)
    val categories: StateFlow<CategoriesResponse?> get() = _categories

    val categoriesList = Pager(PagingConfig(1)) {
        CategoriesPageSource(getCategoriesUseCase)
    }.flow.cachedIn(viewModelScope)
}

