package com.stc.data.locale

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.stc.domain.entity.Category

@Database(entities = [Category::class],
    version = 1, exportSchema = false)
abstract class CategoryDataBase : RoomDatabase() {

    abstract fun getCategoryDao(): CategoryDao


    companion object {
        private const val TAG = "AppDatabase"
        private val LOCK = Any()
        private const val DATABASE_NAME = "Category.db"
        var instance: CategoryDataBase? = null
            private set

        @JvmStatic
        fun initializeDataBase(context: Context?): CategoryDataBase? {
            if (instance == null) {
                synchronized(LOCK) {
                    instance = Room.databaseBuilder(context!!,
                        CategoryDataBase::class.java,
                        DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance
        }
    }
}
