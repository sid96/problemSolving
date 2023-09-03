fun main() {
    println(
        Leetcode62Solution().uniquePaths(
            3, 7
        )
    )
}

class Leetcode62Solution {

    fun uniquePaths(m: Int, n: Int): Int {
        val dpTable = Array(m) { IntArray(n) { 0 } }

        for (rowIndex in 0 until m) {
            for (colIndex in 0 until n) {

                if (rowIndex == 0 || colIndex == 0) {
                    dpTable[rowIndex][colIndex] = 1
                } else {
                    dpTable[rowIndex][colIndex] = dpTable[rowIndex][colIndex - 1] + dpTable[rowIndex - 1][colIndex]
                }

            }
        }

        return dpTable[m - 1][n - 1]
    }

}