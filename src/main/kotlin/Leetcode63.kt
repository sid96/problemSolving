fun main() {
    println(
        Leetcode63Solution().uniquePathsWithObstacles(
            arrayOf(
                intArrayOf(0, 0, 0),
                intArrayOf(0, 0, 0),
                intArrayOf(0, 0, 0),
            )
        )
    )
}

class Leetcode63Solution {

    fun uniquePathsWithObstacles(obstacleGrid: Array<IntArray>): Int {

        val dpTable: Array<IntArray> = Array(101) { IntArray(101) { -1 } }
        return solve(0, 0, obstacleGrid, dpTable)
    }

    fun solve(i: Int, j: Int, obstacleGrid: Array<IntArray>, dpTable: Array<IntArray>): Int {

        if (i >= obstacleGrid.size || j >= obstacleGrid[0].size) {
            return 0
        }

        if (obstacleGrid[i][j] == 1) return 0

        if (i == obstacleGrid.size - 1 && j == obstacleGrid[0].size - 1) {
            return 1
        }

        if (dpTable[i][j] != -1) return dpTable[i][j]

        var ans = 0

        ans += solve(i + 1, j, obstacleGrid, dpTable) + solve(i, j + 1, obstacleGrid, dpTable)

        dpTable[i][j] = ans
        return dpTable[i][j]
    }

}