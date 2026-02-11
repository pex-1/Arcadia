package com.example.arcadia.app.navigation.chrome

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

@Immutable
data class TopBarSpec(
    val visible: Boolean = true,
    val backEnabled: Boolean = false,
)

@Immutable
data class ContentInsetSpec(
    val applyVerticalInset: Boolean = true
)

@Immutable
data class ChromeSpec(
    val topBar: TopBarSpec = TopBarSpec(),
    val insets: ContentInsetSpec = ContentInsetSpec()
)

@Stable
class ChromeController {

    private var overrideSpec: ChromeSpec? by mutableStateOf(null)

    fun override(spec: ChromeSpec) {
        overrideSpec = spec
    }

    fun clearOverride() {
        overrideSpec = null
    }

    internal fun resolve(defaults: ChromeSpec): ChromeSpec {
        return overrideSpec ?: defaults
    }
}