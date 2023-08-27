fun main() {

    println(
        LeetCode403Solution().canCross(
            intArrayOf(0, 1, 2, 3, 4, 8, 9, 11)
        )
    )
}


class LeetCode403Solution {

    fun canCross(stones: IntArray): Boolean {

        if (stones[1] != 1) return false

        val stoneIndexMap = HashMap<Int, Int>()
        val dp = HashMap<Pair<Int, Int>, Boolean>()

        stones.forEachIndexed { index, stone ->
            stoneIndexMap[stone] = index
        }

        return solve(1, 1, stones, stoneIndexMap, dp)

    }

    private fun solve(
        index: Int,
        k: Int,
        stones: IntArray,
        stoneIndexMap: HashMap<Int, Int>,
        dp: HashMap<Pair<Int, Int>, Boolean>
    ): Boolean {

        if (index == stones.size - 1) return true

        if (index > stones.size - 1) return false

        if (dp.containsKey(Pair(index, k))) {
            return dp[Pair(index, k)]!!
        }

        var result = false

        //check for kth index
        if (result.not()) {
            val nextStone = stones[index] + k

            if (isValidJump(nextStone, stoneIndexMap)) {
                val nextIndex = getStoneIndex(nextStone, stoneIndexMap)
                result = result || solve(nextIndex, k, stones, stoneIndexMap, dp)
            }
        }

        //check for (k + 1)th index
        if (result.not()) {
            val nextStone = stones[index] + k + 1

            if (isValidJump(nextStone, stoneIndexMap)) {
                val nextIndex = getStoneIndex(nextStone, stoneIndexMap)
                result = result || solve(nextIndex, k + 1, stones, stoneIndexMap, dp)
            }

        }

        //check for (k - 1)th index
        if (result.not() && k > 1) {
            val nextStone = stones[index] + k - 1

            if (isValidJump(nextStone, stoneIndexMap)) {
                val nextIndex = getStoneIndex(nextStone, stoneIndexMap)
                result = result || solve(nextIndex, k - 1, stones, stoneIndexMap, dp)
            }
        }

        dp[Pair(index, k)] = result
        return dp[Pair(index, k)]!!

    }

    private fun isValidJump(stone: Int, stoneIndexMap: HashMap<Int, Int>) = stoneIndexMap.containsKey(stone)

    private fun getStoneIndex(stone: Int, stoneIndexMap: HashMap<Int, Int>): Int = stoneIndexMap[stone]!!
}