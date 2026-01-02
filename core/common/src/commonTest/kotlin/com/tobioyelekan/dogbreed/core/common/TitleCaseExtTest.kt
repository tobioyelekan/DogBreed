package com.tobioyelekan.dogbreed.core.common

import kotlin.test.Test
import kotlin.test.assertEquals

class TitleCaseExtTest {

    @Test
    fun assertTitleCase(){
        val a = "tobiloba"
        val titleCase = a.toTitleCase()
        assertEquals("Tobiloba", titleCase)
    }
}