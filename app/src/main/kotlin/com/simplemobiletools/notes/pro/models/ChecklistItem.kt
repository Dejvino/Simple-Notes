package com.simplemobiletools.notes.pro.models

import android.icu.text.Collator
import android.os.Build
import com.simplemobiletools.commons.helpers.AlphanumericComparator
import com.simplemobiletools.commons.helpers.SORT_BY_TITLE
import com.simplemobiletools.commons.helpers.SORT_DESCENDING
import kotlinx.serialization.Serializable
import java.util.Comparator

@Serializable
data class ChecklistItem(
    val id: Int,
    val dateCreated: Long = 0L,
    var title: String,
    var isDone: Boolean
) : Comparable<ChecklistItem> {

    companion object {
        var sorting = 0
    }



    override fun compareTo(other: ChecklistItem): Int {
        val comparator = getComparator()
        var result = when {
            sorting and SORT_BY_TITLE != 0 -> comparator.compare(title.lowercase(), other.title.lowercase())
            else -> dateCreated.compareTo(other.dateCreated)
        }

        if (sorting and SORT_DESCENDING != 0) {
            result *= -1
        }

        return result
    }

    private fun getComparator(): Comparator<Any> {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Collator.getInstance() as Comparator<Any>
        } else {
            java.text.Collator.getInstance() as Comparator<Any>
        }
    }
}
