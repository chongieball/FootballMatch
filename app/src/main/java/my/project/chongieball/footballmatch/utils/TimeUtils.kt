package my.project.chongieball.footballmatch.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by chongieball on 06/09/18.
 */
class TimeUtils {

    companion object {

        fun formatDate(date: Date?): String? = with(date?: Date()) {
            SimpleDateFormat("EEE, dd MMM yyy").format(this)
        }

        fun formatGMT(date: String?): Date? {
            val formatter = SimpleDateFormat("dd/MM/yy")
            formatter.timeZone = TimeZone.getTimeZone("UTC")
            val dateTime = "$date"
            return formatter.parse(dateTime)
        }
    }
}