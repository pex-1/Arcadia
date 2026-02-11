package com.example.arcadia.core.data

import com.example.arcadia.core.domain.util.DataError
import com.example.arcadia.core.domain.util.Result
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(
    block: suspend () -> T
): Result<T, DataError.Network> {
    return try {
        Result.Success(block())
    } catch (e: IOException) {
        Result.Error(DataError.Network.NO_INTERNET)
    } catch (e: HttpException) {
        Result.Error(
            when (e.code()) {
                401 -> DataError.Network.UNAUTHORIZED
                500 -> DataError.Network.SERVER_ERROR
                else -> DataError.Network.UNKNOWN
            }
        )
    } catch (e: Exception) {
        Result.Error(DataError.Network.UNKNOWN)
    }
}
