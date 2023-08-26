fun main() {
    println(
        LeetCode646Solution().findLongestChain(
            arrayOf(
                intArrayOf(1,2),
                intArrayOf(2,3),
                intArrayOf(3,4)
            )
        )
    )
}

class LeetCode646Solution {
    fun findLongestChain(pairs: Array<IntArray>): Int {

        var longestChain = 0
        val n = pairs.size
        // val pairMatrix = Array<IntArray>(n) { IntArray(n) { 0 }}
        val dp = IntArray(n) { -1 }

        // createPairMatrix(pairMatrix, pairs)

        pairs.sortBy { pair ->
            pair[0]
        }

        //perform dfs from every ith pair and return max depth
        for(startPairIndex in 0 until n) {
            val chainLengthFromSource = getLongestChainLengthFromSource(startPairIndex, dp, pairs)

            longestChain = maxOf(longestChain, chainLengthFromSource)
        }

        return longestChain

    }

    private fun getLongestChainLengthFromSource(sourceIndex: Int, dp: IntArray, pairs: Array<IntArray>): Int {

        val n = pairs.size

        return getDepthFromDFS(n, sourceIndex, dp, pairs)
    }

    private fun getDepthFromDFS(n: Int, sourceIndex: Int, dp: IntArray, pairs: Array<IntArray>): Int {

        if(dp[sourceIndex] != -1) {
            return dp[sourceIndex]
        }

        var depth = 1

        for(index in 0 until n) {
            if(pairs[sourceIndex][1] < pairs[index][0]) {
                depth = maxOf(depth, 1 + getDepthFromDFS(n, index, dp, pairs))
            }
        }

        dp[sourceIndex] = depth
        return dp[sourceIndex]

    }

    // private fun createPairMatrix(pairMatrix: Array<IntArray>, pairs: Array<IntArray>) {
    //     val n = pairMatrix.size

    //     for(row in 0 until n) {
    //         for(col in 0 until n) {
    //             if(isChained(pairs[row], pairs[col])) {
    //                     pairMatrix[row][col] = 1
    //             }
    //         }
    //     }
    // }

    // private fun isChained(pair1: IntArray, pair2: IntArray): Boolean {
    //     return pair1[1] < pair2[0]
    // }

}