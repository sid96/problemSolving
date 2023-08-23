import java.util.*
import kotlin.math.sqrt

fun main() {
    println(
        LeetCode2101Solution().maximumDetonation(
            arrayOf(
                intArrayOf(54, 95, 4),
                intArrayOf(99, 46, 3),
                intArrayOf(29, 21, 3),
                intArrayOf(96, 72, 8),
                intArrayOf(49, 43, 3),
                intArrayOf(11, 20, 3),
                intArrayOf(2, 57, 1),
                intArrayOf(69, 51, 7),
                intArrayOf(97, 1, 10),
                intArrayOf(85, 45, 2),
                intArrayOf(38, 47, 1),
                intArrayOf(83, 75, 3),
                intArrayOf(65, 59, 3),
                intArrayOf(33, 4, 1),
                intArrayOf(32, 10, 2),
                intArrayOf(20, 97, 8),
                intArrayOf(35, 37, 3),
            )
        )
    )
}


class LeetCode2101Solution {

    fun maximumDetonation(bombs: Array<IntArray>): Int {

        var result = 0

        val bombMatrix = Array(bombs.size) { IntArray(bombs.size) { -1 } }

        for (row in bombs.indices) {

            for (column in bombs.indices) {

                val distanceBetweenBombs = getDistanceBetweenBombs(bombs[row], bombs[column])

                if (distanceBetweenBombs <= bombs[row][2].toLong()) {
                    bombMatrix[row][column] = 1
                } else {
                    bombMatrix[row][column] = 0
                }

            }

        }


        for (bombIndex in bombs.indices) {

            result = maxOf(result, bfs(bombIndex, bombMatrix))

            if (result == bombs.size) break
        }

        return result

    }

    private fun bfs(bombIndex: Int, bombMatrix: Array<IntArray>): Int {

        val visited = IntArray(bombMatrix.size) { 0 }
        val queue = LinkedList<Int>()

        visited[bombIndex] = 1
        queue.addFirst(bombIndex)
        var count = 1

        while (queue.isNotEmpty()) {

            val currentBombIndex = queue.removeFirst()

            for (nextBomb in bombMatrix.indices) {

                if (bombMatrix[currentBombIndex][nextBomb] == 1 && visited[nextBomb] == 0) {
                    visited[nextBomb] = 1
                    queue.addLast(nextBomb)
                    count++
                }

            }

        }

        return count
    }

    private fun getDistanceBetweenBombs(bomb1: IntArray, bomb2: IntArray): Double {
        return sqrt(
            (getSquare(bomb2[1] - bomb1[1])) + getSquare(bomb2[0] - bomb1[0]).toDouble()
        )
    }

    private fun getSquare(number: Int): Long {
        return number.toLong() * number.toLong()
    }
}