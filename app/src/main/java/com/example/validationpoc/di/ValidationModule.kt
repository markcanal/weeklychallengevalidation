package com.example.validationpoc.di

import com.example.validationpoc.ui.usecase.ManageDataEntry
import com.example.validationpoc.ui.usecase.ManageDataEntryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ValidationModule {
    @Provides
    @Singleton
    fun provideManageDataEntry(): ManageDataEntry {
        return ManageDataEntryImpl()
    }
}