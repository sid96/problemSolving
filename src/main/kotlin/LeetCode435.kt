fun main() {

    println(
        LeetCode435Solution().eraseOverlapIntervals(
            arrayOf(
                intArrayOf(1,7),
                intArrayOf(4,5),
                intArrayOf(5,6),
                intArrayOf(7,9)
            )
        )
    )
}


class LeetCode435Solution() {

    var maxIntervalsTaken = 0

    fun eraseOverlapIntervals(intervals: Array<IntArray>): Int {

        intervals.sortBy {
            it[0]
        }

        val dp = HashMap<Int, Int>()

        for(i in intervals.size - 1 downTo 0) {

            maxIntervalsTaken  = maxOf(maxIntervalsTaken, 1 + solve(i, i, intervals, dp))
        }


        return /*intervals.size - */maxIntervalsTaken
    }

    private fun solve(startIndex: Int, currentIndex: Int, intervals: Array<IntArray>, dp: HashMap<Int, Int>): Int {

        var intervalsTaken = 0

        if (currentIndex == intervals.size) {
            return 0
        }

        for (i in currentIndex until intervals.size) {

            if (intervals[i][0] >= intervals[startIndex][1]) {

                if(dp.containsKey(i)) {
                    intervalsTaken = maxOf(intervalsTaken, 1 + dp[i]!!)
                    break
                }

                intervalsTaken = maxOf(intervalsTaken, 1 + solve(i, i, intervals, dp))
            }

        }

        dp[startIndex] = intervalsTaken

        return intervalsTaken

    }
}

/*
[1,2]
[2,3]
[3,4]
[4,5]
*/
