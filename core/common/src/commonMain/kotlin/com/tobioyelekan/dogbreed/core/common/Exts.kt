package com.tobioyelekan.dogbreed.core.common.util

fun String.toTitleCase(): String {
    return this.replaceFirstChar { it.uppercase() }
}