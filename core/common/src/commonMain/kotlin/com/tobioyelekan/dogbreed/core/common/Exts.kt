package com.tobioyelekan.dogbreed.core.common

fun String.toTitleCase(): String {
    return this.replaceFirstChar { it.uppercase() }
}