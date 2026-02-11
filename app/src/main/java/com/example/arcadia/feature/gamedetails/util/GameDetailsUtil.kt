package com.example.arcadia.feature.gamedetails.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.arcadia.core.presentation.designsystem.theme.AndroidIcon
import com.example.arcadia.core.presentation.designsystem.theme.AppleIcon
import com.example.arcadia.core.presentation.designsystem.theme.LinuxIcon
import com.example.arcadia.core.presentation.designsystem.theme.NintendoSwitchIcon
import com.example.arcadia.core.presentation.designsystem.theme.PlaystationIcon
import com.example.arcadia.core.presentation.designsystem.theme.WiiIcon
import com.example.arcadia.core.presentation.designsystem.theme.WindowsIcon
import com.example.arcadia.core.presentation.designsystem.theme.XboxIcon
import com.example.arcadia.core.domain.model.PlatformType

@Composable
fun PlatformType.getPlatformImage(): ImageVector? {
    return when (this) {
        PlatformType.PLAYSTATION -> PlaystationIcon
        PlatformType.XBOX -> XboxIcon
        PlatformType.WINDOWS -> WindowsIcon
        PlatformType.NINTENDO_SWITCH -> NintendoSwitchIcon
        PlatformType.ANDROID -> AndroidIcon
        PlatformType.APPLE -> AppleIcon
        PlatformType.LINUX -> LinuxIcon
        PlatformType.WII -> WiiIcon
        PlatformType.OTHER -> null
    }
}