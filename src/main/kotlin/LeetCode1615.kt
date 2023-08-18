fun main() {

    println(
        LeetCode1615Solution().maximalNetworkRank(
            8,
            arrayOf(
                intArrayOf(0, 1),
                intArrayOf(1, 2),
                intArrayOf(2, 3),
                intArrayOf(2, 4),
                intArrayOf(5, 6),
                intArrayOf(5, 7),
            )
        )
    )
}


class LeetCode1615Solution {
    fun maximalNetworkRank(n: Int, roads: Array<IntArray>): Int {
        var result = Int.MIN_VALUE

        val edgeExists = Array(n) { IntArray(n) { 0 } }
        val degree = Array(n) { 0 }

        roads.forEach { edge ->
            edgeExists[edge[0]][edge[1]]++
            edgeExists[edge[1]][edge[0]]++
            degree[edge[0]]++
            degree[edge[1]]++
        }

        for (i in 0 until n) {
            for (j in 0 until n) {

                if(i == j) continue

                val rank = degree[i] + degree[j] - edgeExists[i][j]
                result = maxOf(result, rank)
            }
        }

        return result
    }
}