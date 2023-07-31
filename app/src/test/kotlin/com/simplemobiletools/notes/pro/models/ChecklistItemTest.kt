package com.simplemobiletools.notes.pro.models

import com.simplemobiletools.commons.helpers.SORT_BY_DATE_CREATED
import com.simplemobiletools.commons.helpers.SORT_BY_TITLE
import com.simplemobiletools.commons.helpers.SORT_DESCENDING
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
class ChecklistItemTest(val sorting: String, val itemOne: ChecklistItem, val expectedCompareTo: Int, val itemTwo: ChecklistItem) {

    @Before
    fun setup() {
        ChecklistItem.sorting = sortBy[sorting]!!
    }

    @Test
    fun testCompareTo() {
        assertEquals("A = $itemOne\nB = $itemTwo\nA.compareTo(B) (sort by $sorting):\n",
            expectedCompareTo, coerce(itemOne.compareTo(itemTwo)))
    }

    private val sortBy = mapOf<String, Int>(
        "title" to SORT_BY_TITLE,
        "title descending" to (SORT_BY_TITLE or SORT_DESCENDING),
        "date" to SORT_BY_DATE_CREATED
    )

    private fun coerce(compareToResult: Int): Int {
        return compareToResult.coerceIn(-1, 1)
    }

    companion object {
        private const val BEFORE = -1
        private const val AFTER = 1
        private const val EQUALS = 0

        @JvmStatic
        @Parameters()
        fun data(): List<Array<Any>> {
            return listOf(
                arrayOf("title", itemTitled("A"), EQUALS, itemTitled("A")),
                arrayOf("title", itemTitled("A"), BEFORE, itemTitled("B")),
                arrayOf("title", itemTitled("D"), AFTER, itemTitled("C")),
                arrayOf("title", itemTitled("x"), AFTER, itemTitled("W")),
                arrayOf("title descending", itemTitled("x"), BEFORE, itemTitled("W")),
                arrayOf("title", itemTitled("Buy apples"), BEFORE, itemTitled("Buy bananas")),
                arrayOf("title", itemTitled("15"), BEFORE, itemTitled("19")),
                arrayOf("title", itemTitled("200"), AFTER, itemTitled("95")),
                arrayOf("title", itemTitled("Song 5"), BEFORE, itemTitled("Song 12")),
                arrayOf("title", itemTitled("IMG_20"), BEFORE, itemTitled("IMG_52")),
                arrayOf("title", itemTitled("IMG_115"), AFTER, itemTitled("IMG_85")),
                //arrayOf("title", itemTitled("Échalote (French: shallot)"), BEFORE, itemTitled("French fries")),
                //arrayOf("title", itemTitled("yoghurt"), AFTER, itemTitled("œuf (French: egg)")),
                arrayOf("date", itemTitled("aa"), EQUALS, itemTitled("zzz")),
                arrayOf("date", itemCreated(10L), BEFORE, itemCreated(20L)),
                arrayOf("date", itemCreated(400L), AFTER, itemCreated(310L)),
            )
        }

        private fun itemTitled(title: String) = ChecklistItem(title.hashCode(), 0, title, false)
        private fun itemCreated(dateCreated: Long) = ChecklistItem(dateCreated.hashCode(), dateCreated, "", false)
    }
}
