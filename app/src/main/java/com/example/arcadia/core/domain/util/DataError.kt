package com.example.arcadia.core.domain.util

sealed interface DataError: Error {
    enum class Network: DataError {
        REQUEST_TIMEOUT,
        UNAUTHORIZED,
        CONFLICT,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
        SERVER_ERROR,
        UNKNOWN
    }

    enum class Local: DataError {
        DISK_FULL,
        UNKNOWN
    }
}