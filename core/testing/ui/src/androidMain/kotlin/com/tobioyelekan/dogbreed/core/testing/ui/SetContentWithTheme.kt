package com.tobioyelekan.dogbreed.core.testing.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import org.jetbrains.compose.resources.PreviewContextConfigurationEffect

fun ComposeContentTestRule.setContentWithTheme(composable: @Composable () -> Unit) {
    setContent {
        CompositionLocalProvider(LocalInspectionMode provides true) {
            PreviewContextConfigurationEffect()
            composable()
        }
    }
}