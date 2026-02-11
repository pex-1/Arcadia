package com.example.arcadia.core.domain.mapper

import com.example.arcadia.core.domain.model.PlatformType

fun PlatformType.Companion.fromRaw(raw: String): PlatformType {
    val normalized = raw.lowercase()

    return when {
        "playstation" in normalized -> PlatformType.PLAYSTATION
        "xbox" in normalized -> PlatformType.XBOX
        "pc" in normalized || "windows" in normalized -> PlatformType.WINDOWS
        "nintendo" in normalized || "switch" in normalized -> PlatformType.NINTENDO_SWITCH
        "android" in normalized -> PlatformType.ANDROID
        "ios" in normalized || "mac" in normalized || "apple" in normalized -> PlatformType.APPLE
        "linux" in normalized -> PlatformType.LINUX
        "wii" in normalized -> PlatformType.WII
        else -> PlatformType.OTHER
    }
}