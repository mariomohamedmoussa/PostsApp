package com.stc.postsapp.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.stc.postsapp.R
import com.stc.postsapp.databinding.ActivityMainBinding
import com.stc.present.viewmodel.CategoriesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var adapter: CategoryAdapter
    private val TAG = "MainActivity"
    lateinit var  binding:ActivityMainBinding
    val viewModel: CategoriesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        initGUI()

        lifecycleScope.launchWhenStarted {
            viewModel.categoriesList.collect {

                it.let { adapter.submitData(it) }
            }
        }
        }

        private fun initGUI() {
            binding.apply {
                rvActivityMainCategories. adapter = adapter
                rvActivityMainCategories.layoutManager = LinearLayoutManager(this@MainActivity)


            }


        }
    }
