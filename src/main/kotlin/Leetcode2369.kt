fun main() {
    println(
        Leetcode2369Solution().validPartition(
            intArrayOf(5,6)
        )
    )
}

class Leetcode2369Solution {

    fun validPartition(nums: IntArray): Boolean {

        val dp = HashMap<Int, Boolean>()
        return solve(0, 2, nums, dp) || solve(0, 3, nums, dp)
    }

    fun solve(start: Int, end: Int, nums: IntArray, dp: HashMap<Int, Boolean>): Boolean {

        if(dp.containsKey(end)) {
            return dp[end]!!
        }

        if(isValidPartition(start, end, nums)) {

            if(end == nums.size) {
                return true
            }

            val partitionByTwo = solve(end, end + 2, nums, dp)
            val partitionByThree = solve(end, end + 3, nums, dp)

            dp[end] = partitionByTwo || partitionByThree
            return dp[end]!!

        } else {
            return false
        }
    }

    private fun isValidPartition(start: Int, end: Int, nums: IntArray): Boolean {
        if(end > nums.size) return false

        val partitionSize = end - start

        return if (partitionSize == 2) {
            nums[start] == nums[start + 1]
        } else {
            (nums[start] == nums[start + 1] && nums[start + 1] == nums[start + 2]) ||
                    (nums[start + 1] - nums[start] == 1 && nums[start + 2] - nums[start + 1] == 1)
        }
    }
}