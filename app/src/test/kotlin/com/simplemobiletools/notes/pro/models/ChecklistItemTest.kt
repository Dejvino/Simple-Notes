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
        assertEquals(expectedCompareTo, itemOne.compareTo(itemTwo))
    }

    companion object {
        const val BEFORE = -1
        const val AFTER = 1
        const val EQUALS = 0

        @JvmStatic
        @Parameters()
        fun data(): List<Array<Any>> {
            return listOf(
                arrayOf(SORT_BY_TITLE,
                    ChecklistItem(1, 0, "A", false),
                    BEFORE, ChecklistItem(2, 0, "B", false)),
            )
        }
    }
}
