package com.example.arcadia.core.database.di

import android.content.Context
import androidx.room.Room
import com.example.arcadia.core.database.ArcadiaDatabase
import com.example.arcadia.core.database.util.DatabaseConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): ArcadiaDatabase {
        return Room.databaseBuilder(
            context,
            ArcadiaDatabase::class.java,
            DatabaseConstants.DATABASE_NAME
        )
            .build()

    }

    @Provides
    fun provideGenreDao(arcadiaDatabase: ArcadiaDatabase) = arcadiaDatabase.genreDao

    @Provides
    fun provideGameDao(arcadiaDatabase: ArcadiaDatabase) = arcadiaDatabase.gameDao
}
