package com.stc.present.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stc.domain.entity.CategoriesResponse
import com.stc.domain.usecase.CategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(private val getCategoriesUseCase: CategoriesUseCase): ViewModel() {
    private  val TAG = "CategoriesViewModel"
    private val _categories:MutableStateFlow<CategoriesResponse?> = MutableStateFlow(null)
     val categories:StateFlow<CategoriesResponse?> get() = _categories
    fun getCategories(){
        viewModelScope.launch {
            try {
            _categories.value = getCategoriesUseCase()
            }catch (e :Exception){
                Log.e(TAG, "getCategories: "+ e.message.toString())
            }
        }
    }
}