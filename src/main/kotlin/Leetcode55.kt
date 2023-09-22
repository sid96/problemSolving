fun main() {
    println(
        Leetcode55Solution().canJump(
            intArrayOf(5, 4, 3, 2, 1, 0, 1)
        )
    )
}

class Leetcode55Solution {

    fun canJump(nums: IntArray): Boolean {
        val dp = IntArray(nums.size) { -1 }
        return solve(0, nums, dp)
    }

    private fun solve(currentIndex: Int, nums: IntArray, dp: IntArray): Boolean {

        if (currentIndex == nums.size - 1)
            return true

        if (currentIndex >= nums.size)
            return false

        if (dp[currentIndex] != -1) {
            return dp[currentIndex] == 1
        }

        var result = false

        for (i in 1..nums[currentIndex]) {

            if (currentIndex + i < nums.size) {

                result = result || solve(currentIndex + i, nums, dp)

                if (result) break

            }

        }

        dp[currentIndex] = if (result) 1 else 0
        return result
    }

}