package com.example.arcadia.core.data.di

import com.example.arcadia.core.data.SettingsDataStoreImpl
import com.example.arcadia.core.data.repository.GamePagingRepository
import com.example.arcadia.core.data.repository.GamePagingRepositoryImpl
import com.example.arcadia.core.data.repository.GenreRepositoryImpl
import com.example.arcadia.core.data.repository.GameRepositoryImpl
import com.example.arcadia.core.database.datasource.RoomLocalGameDataSource
import com.example.arcadia.core.database.datasource.RoomLocalGenreDataSource
import com.example.arcadia.core.domain.SettingsDataStore
import com.example.arcadia.core.domain.repository.GameRepository
import com.example.arcadia.core.domain.repository.GenreRepository
import com.example.arcadia.core.domain.datasource.LocalGameDataSource
import com.example.arcadia.core.domain.datasource.LocalGenreDataSource
import com.example.arcadia.core.domain.datasource.RemoteGameDataSource
import com.example.arcadia.core.domain.datasource.RemoteGenreDataSource
import com.example.arcadia.core.network.datasource.RemoteGameDataSourceImpl
import com.example.arcadia.core.network.datasource.RemoteGenreDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds @Singleton
    abstract fun bindSettingsDataStore(impl: SettingsDataStoreImpl): SettingsDataStore

    @Binds @Singleton
    abstract fun bindRemoteGenreDataSource(impl: RemoteGenreDataSourceImpl): RemoteGenreDataSource

    @Binds @Singleton
    abstract fun bindGenreRepository(impl: GenreRepositoryImpl): GenreRepository

    @Binds @Singleton
    abstract fun bindLocalGenreDataSource(impl: RoomLocalGenreDataSource): LocalGenreDataSource

    @Binds @Singleton
    abstract fun bindRemoteGameDataSource(impl: RemoteGameDataSourceImpl): RemoteGameDataSource

    @Binds @Singleton
    abstract fun bindLocalGameDataSource(impl: RoomLocalGameDataSource): LocalGameDataSource

    @Binds @Singleton
    abstract fun bindGameRepository(impl: GameRepositoryImpl): GameRepository


    @Binds
    @Singleton
    abstract fun bindGamePagingRepository(impl: GamePagingRepositoryImpl): GamePagingRepository
}
