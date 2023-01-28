package com.natiqhaciyef.dailybudgettracker.data.di

import android.content.Context
import androidx.room.Room
import com.natiqhaciyef.dailybudgettracker.data.repository.AppRepository
import com.natiqhaciyef.dailybudgettracker.data.roomdb.AppDao
import com.natiqhaciyef.dailybudgettracker.data.roomdb.AppDatabase
import com.natiqhaciyef.dailybudgettracker.data.source.AppDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDao(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java,"app_database")
            .build()
            .getDao()

    @Provides
    @Singleton
    fun provideDataSource(dao: AppDao) = AppDataSource(dao)

    @Provides
    @Singleton
    fun provideRepository(ds: AppDataSource) = AppRepository(ds)
}