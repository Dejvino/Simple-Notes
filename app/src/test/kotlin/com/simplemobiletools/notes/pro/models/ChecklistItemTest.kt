package com.simplemobiletools.notes.pro.models

import com.simplemobiletools.commons.helpers.SORT_BY_DATE_CREATED
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
                arrayOf(SORT_BY_TITLE, itemTitled("x"), AFTER, itemTitled("W")),
                arrayOf(SORT_BY_TITLE, itemTitled("Buy apples"), BEFORE, itemTitled("Buy bananas")),
                arrayOf(SORT_BY_TITLE, itemTitled("15"), BEFORE, itemTitled("19")),
                arrayOf(SORT_BY_TITLE, itemTitled("200"), AFTER, itemTitled("95")),
                arrayOf(SORT_BY_TITLE, itemTitled("Song 5"), BEFORE, itemTitled("Song 12")),
                arrayOf(SORT_BY_TITLE, itemTitled("IMG_20"), BEFORE, itemTitled("IMG_52")),
                arrayOf(SORT_BY_TITLE, itemTitled("IMG_115"), AFTER, itemTitled("IMG_85")),
                //arrayOf(SORT_BY_TITLE, itemTitled("Échalote (French: shallot)"), BEFORE, itemTitled("French fries")),
                //arrayOf(SORT_BY_TITLE, itemTitled("yoghurt"), AFTER, itemTitled("œuf (French: egg)")),
                arrayOf(SORT_BY_DATE_CREATED, itemTitled("aa"), EQUALS, itemTitled("zzz")),
                arrayOf(SORT_BY_DATE_CREATED, itemCreated(10L), BEFORE, itemCreated(20L)),
                arrayOf(SORT_BY_DATE_CREATED, itemCreated(400L), AFTER, itemCreated(310L)),
            )
        }

        private fun itemTitled(title: String) = ChecklistItem(title.hashCode(), 0, title, false)
        private fun itemCreated(dateCreated: Long) = ChecklistItem(dateCreated.hashCode(), dateCreated, "", false)
    }
}
