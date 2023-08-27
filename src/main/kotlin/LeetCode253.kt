import java.util.*

fun main() {

    println(
        LeetCode253Solution().minMeetingRooms(
            arrayOf(
                intArrayOf(26, 29),
                intArrayOf(19, 26),
                intArrayOf(19, 28),
                intArrayOf(4, 19),
                intArrayOf(4, 25),
            )
        )
    )
}


class LeetCode253Solution {

    fun minMeetingRooms(intervals: Array<IntArray>): Int {

        val priorityQueue = PriorityQueue<Int> { item1, item2 ->
            item1 - item2
        }

        intervals.sortBy { interval ->
            interval[0]
        }

        priorityQueue.add(intervals[0][1])

        for(index in 1 until intervals.size) {

            if(intervals[index][0] >= priorityQueue.peek()) {
                priorityQueue.poll()
            }

            priorityQueue.add(intervals[index][1])
        }

        return priorityQueue.size

    }
}