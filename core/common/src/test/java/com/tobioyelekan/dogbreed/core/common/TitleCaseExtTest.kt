package com.tobioyelekan.dogbreed.core.common

import com.tobioyelekan.dogbreed.core.common.util.toTitleCase
import org.junit.Test
import kotlin.test.assertEquals

class TitleCaseExtTest {

    @Test
    fun assertTitleCase(){
        val a = "tobiloba"
        val titleCase = a.toTitleCase()
        assertEquals("Tobiloba", titleCase)
    }
}