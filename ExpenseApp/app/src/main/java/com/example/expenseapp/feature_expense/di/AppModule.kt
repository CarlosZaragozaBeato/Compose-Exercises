package com.example.expenseapp.feature_expense.di

import android.content.Context
import androidx.room.Room
import com.example.expenseapp.feature_expense.data.ExpenseDao
import com.example.expenseapp.feature_expense.data.ExpenseDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun providesExpenseDao(expenseDatabase:ExpenseDataBase):
                ExpenseDao = expenseDatabase.expenseDao()

    @Singleton
    @Provides
    fun provideAppDataBase(@ApplicationContext context: Context):ExpenseDataBase{
        return Room.databaseBuilder(
            context,
            ExpenseDataBase::class.java,
            "expense_db"
        ).fallbackToDestructiveMigration()
            .build()
    }
}