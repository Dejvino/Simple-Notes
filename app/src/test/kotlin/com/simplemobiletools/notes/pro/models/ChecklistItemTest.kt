package com.simplemobiletools.notes.pro.models

import com.simplemobiletools.commons.helpers.SORT_BY_TITLE
import org.junit.Assert.*
import org.junit.Before
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
class ChecklistItemTest(val sorting: Int, val itemOne: ChecklistItem, val expectedCompareTo: Int, val itemTwo: ChecklistItem) {

    @Before
    fun setup() {
        ChecklistItem.sorting = sorting
    }

    @Test
    fun testCompareTo() {
        assertEquals("A = $itemOne\nB = $itemTwo\nA.compareTo(B):\n",
            expectedCompareTo, coerce(itemOne.compareTo(itemTwo)))
    }

    private fun coerce(compareToResult: Int): Int {
        return compareToResult.coerceIn(-1, 1)
    }

    companion object {
        const val BEFORE = -1
        const val AFTER = 1
        const val EQUALS = 0

        @JvmStatic
        @Parameters()
        fun data(): List<Array<Any>> {
            return listOf(
                arrayOf(SORT_BY_TITLE, itemTitled("A"), EQUALS, itemTitled("A")),
                arrayOf(SORT_BY_TITLE, itemTitled("A"), BEFORE, itemTitled("B")),
                arrayOf(SORT_BY_TITLE, itemTitled("D"), AFTER, itemTitled("C")),
                arrayOf(SORT_BY_TITLE, itemTitled("15"), BEFORE, itemTitled("19")),
                arrayOf(SORT_BY_TITLE, itemTitled("200"), AFTER, itemTitled("95")),
            )
        }

        private fun itemTitled(title: String) = ChecklistItem(title.hashCode(), 0, title, false)
    }
}
