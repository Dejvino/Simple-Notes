package com.simplemobiletools.notes.pro.helpers

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(Parameterized::class)
class CollatorBasedComparatorTest(val itemOne: String, val expectedCompare: Int, val itemTwo: String) {

    @Test
    fun testCompare() {
        val collatorBasedComparator = CollatorBasedComparator()
        assertEquals("compare(\"$itemOne\", \"$itemTwo\") = ",
            expectedCompare, collatorBasedComparator.compare(itemOne, itemTwo))
    }

    companion object {
        private const val BEFORE = -1
        private const val AFTER = 1
        private const val EQUALS = 0

        @JvmStatic
        @Parameters()
        fun data(): List<Array<Any>> {
            return listOf(
                arrayOf("A", EQUALS, "A"),
                arrayOf("A", BEFORE, "B"),
                arrayOf("D", AFTER, "C"),
                arrayOf("x", AFTER, "W"),
                arrayOf("X", AFTER, "w"),
                arrayOf("Buy apples", BEFORE, "Buy bananas"),
                arrayOf("15", BEFORE, "19"),
                arrayOf("200", AFTER, "95"),
                arrayOf("Song 5", BEFORE, "Song 12"),
                arrayOf("IMG_20", BEFORE, "IMG_52"),
                arrayOf("IMG_115", AFTER, "IMG_85"),
                arrayOf("Échalote (French: shallot)", BEFORE, "French fries"),
                arrayOf("Échalote", EQUALS, "Echalote"),
                arrayOf("à\\u0325", EQUALS, "a\\u0325"),
                arrayOf("yoghurt", AFTER, "œuf (French: egg)"),
            )
        }
    }
}
