package com.stc.postsapp.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.stc.postsapp.R
import com.stc.present.viewmodel.CategoriesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var categoryAdapter:CategoryAdapter
    val viewModel: CategoriesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initGUI()

        viewModel.getCategories()

        lifecycleScope.launchWhenStarted {
            viewModel.categories.collect{
                it?.categories?.let { it1 -> categoryAdapter.add(it1) }
            }
        }
    }

    private fun initGUI() {
        categoryAdapter = CategoryAdapter(this)
        rv_activity_main_categories.adapter = categoryAdapter
        rv_activity_main_categories.layoutManager = LinearLayoutManager(this)


    }
}
