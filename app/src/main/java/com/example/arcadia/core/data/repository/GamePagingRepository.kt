package com.example.arcadia.core.data.repository

import androidx.paging.PagingData
import com.example.arcadia.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface GamePagingRepository {
    fun getPagedGames(): Flow<PagingData<Game>>
}