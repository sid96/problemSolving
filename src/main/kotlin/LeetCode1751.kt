import kotlin.math.max

fun main() {
    println(
        LeetCode1751Solution().maxValue(
            arrayOf(
                intArrayOf(1,1,1),
                intArrayOf(2, 2, 2),
                intArrayOf(3, 3, 3),
                intArrayOf(4, 4, 4),
                intArrayOf(5, 5, 5),
            ), 2
        )
    )
}


internal class LeetCode1751Solution {

    data class Event(
        val startDay: Int = -1,
        val endDay: Int = -1,
        val value: Int = -1
    )

    fun maxValue(events: Array<IntArray>, k: Int): Int {

        val eventsList = Array(events.size) { Event() }

        events.forEachIndexed { index, event ->
            eventsList[index] = Event(event[0], event[1], event[2])
        }

        eventsList.sortBy {
            it.startDay
        }

        var result = 0

        val dpTable = HashMap<Pair<Int, Int>, Int>() //(index, k) -> value

        for (i in events.indices) {
            val totalValueFromIndexI = solve(eventsList, i, k-1, dpTable)
            result = maxOf(result, totalValueFromIndexI)
        }


        return result

    }

    private fun solve(eventsList: Array<Event>, currentIndex: Int, eventsLeft: Int, dpTable: HashMap<Pair<Int, Int>, Int>): Int {

        if(dpTable.containsKey(Pair(currentIndex, eventsLeft))) {
            return dpTable[Pair(currentIndex, eventsLeft)]!!
        }

        var totalMaxValueFromCurrentIndex = eventsList[currentIndex].value

        if(eventsLeft > 0) {
            for(nextIndex in currentIndex + 1 until eventsList.size) {

                if(eventsList[currentIndex].endDay < eventsList[nextIndex].startDay) {


                    val resultFromCurrentIndex = eventsList[currentIndex].value + solve(eventsList, nextIndex, eventsLeft-1, dpTable)


                    totalMaxValueFromCurrentIndex = maxOf(totalMaxValueFromCurrentIndex, resultFromCurrentIndex)

                }

            }
        }

        dpTable[Pair(currentIndex, eventsLeft)] = totalMaxValueFromCurrentIndex

        return totalMaxValueFromCurrentIndex
    }
}