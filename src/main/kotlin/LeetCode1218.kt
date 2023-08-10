import com.sun.source.tree.DoWhileLoopTree
import java.util.Collections
import java.util.PriorityQueue

fun main() {
    println(
        LeetCode1218Solution().longestSubsequence(
            intArrayOf(3, 4, 2, 5),
            5
        )
    )
}


class LeetCode1218Solution {
    fun longestSubsequence(arr: IntArray, difference: Int): Int {

        var result = 0

        val dp = IntArray(arr.size) { -1 }

        for (i in arr.size - 1 downTo 0) {
            val lengthOfAPFromi = solve(i, i + 1, difference, arr, dp)
            dp[i] = lengthOfAPFromi
            result = maxOf(result, lengthOfAPFromi)
        }

        return result

    }

    fun solve(previousIndex: Int, currentIndex: Int, diff: Int, arr: IntArray, dp: IntArray): Int {

        if (currentIndex >= arr.size) {
            return 1
        }

        if (arr[currentIndex] - arr[previousIndex] == diff) {

            if (dp[currentIndex] != -1) {
                return 1 + dp[currentIndex]
            } else {
                dp[previousIndex] = 1 + solve(currentIndex, currentIndex + 1, diff, arr, dp)
                return dp[previousIndex]
            }

        } else {
            return solve(previousIndex, currentIndex + 1, diff, arr, dp)
        }
    }
}