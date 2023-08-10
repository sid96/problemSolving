fun main() {
    print(Leetcode435Solution().eraseOverlapIntervals(
        arrayOf(
            intArrayOf(1,7),
            intArrayOf(4,6),
            intArrayOf(6,8),
            intArrayOf(8,9),
        )
    ))
}

class Leetcode435Solution {

    var maxIntervalsTaken = 0

    fun eraseOverlapIntervals(intervals: Array<IntArray>): Int {

        intervals.sortBy {
            it[0]
        }

        for(i in intervals.indices) {
            maxIntervalsTaken  = maxOf(maxIntervalsTaken, 1 + solve(i, i, intervals))
        }


        return intervals.size - maxIntervalsTaken
    }

    private fun solve(startIndex: Int, currentIndex: Int, intervals: Array<IntArray>): Int {

        var intervalsTaken = 0

        if (currentIndex == intervals.size) {
            return 0
        }

        for (i in currentIndex until intervals.size) {

            if (intervals[i][0] >= intervals[startIndex][1]) {
                intervalsTaken = maxOf(intervalsTaken, 1 + solve(i, i, intervals))
            }

        }

        return intervalsTaken

    }
}