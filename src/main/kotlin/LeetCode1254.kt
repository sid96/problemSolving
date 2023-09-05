import java.util.*

fun main() {

    println(
        LeetCode1254Solution().closedIsland(
            arrayOf(
                intArrayOf(1, 1, 1, 1, 1, 1, 1, 0),
                intArrayOf(1, 0, 0, 0, 0, 1, 1, 0),
                intArrayOf(1, 0, 1, 0, 1, 1, 1, 0),
                intArrayOf(1, 0, 0, 0, 0, 1, 0, 1),
                intArrayOf(1, 0, 0, 0, 0, 1, 0, 1),
                intArrayOf(1, 1, 1, 1, 1, 1, 1, 0)
            )
        )
    )
}


class LeetCode1254Solution {

    private val directions = arrayOf(
        intArrayOf(-1, 0),
        intArrayOf(0, 1),
        intArrayOf(1, 0),
        intArrayOf(0, -1)
    )

    data class Cell(
        val x: Int,
        val y: Int
    )

    fun closedIsland(grid: Array<IntArray>): Int {

        var result = 0

        val n = grid.size
        val m = grid[0].size

        for (row in 0 until n) {
            for (col in 0 until m) {
                if (grid[row][col] == 0) {
                    val isIsland = bfs(grid, row, col, n, m)
                    if (isIsland) result++
                }
            }
        }

        return result
    }

    private fun bfs(grid: Array<IntArray>, row: Int, col: Int, n: Int, m: Int): Boolean {
        val startCell = Cell(row, col)
        val queue = LinkedList<Cell>()

        queue.addLast(startCell)
        grid[startCell.x][startCell.y] = 1

        var isIsland = true

        while (queue.isNotEmpty()) {

            val currentCell = queue.removeFirst()

            for (direction in directions) {
                val adjX = currentCell.x + direction[0]
                val adjY = currentCell.y + direction[1]

                if (adjX in 0 until n && adjY in 0 until m) {

                    if (grid[adjX][adjY] == 0) {
                        grid[adjX][adjY] = 1
                        queue.addLast(Cell(adjX, adjY))
                    }

                } else {
                    isIsland = false
                }
            }

        }

        return isIsland
    }
}