import java.util.LinkedList

fun main() {
    val result = LeetCode542Solution().updateMatrix(
//        arrayOf(
//            intArrayOf(1, 1, 1, 1, 1),
//            intArrayOf(1, 1, 0, 0, 1),
//            intArrayOf(1, 0, 1, 1, 0),
//            intArrayOf(0, 0, 0, 0, 0)
//        )
        arrayOf(
            intArrayOf(0, 0, 0),
            intArrayOf(0, 1, 0),
            intArrayOf(0, 0, 0)
        )
    )

    result.forEach {
        it.forEach {
            print("$it ")
        }
        println()
    }
}

class LeetCode542Solution {

    fun updateMatrix(mat: Array<IntArray>): Array<IntArray> {

        val result = Array<IntArray>(mat.size) { IntArray(mat[0].size) { -1 } }
        val visited = Array<IntArray>(mat.size) { IntArray(mat[0].size) { 0 } }

        val queue = LinkedList<Pair<Int, Int>>()

        for (i in mat.indices) {
            for (j in 0 until mat[0].size) {
                if (mat[i][j] == 0) {
                    visited[i][j] = 1
                    queue.addLast(Pair(i, j))
                }
            }
        }

        var dist = 0

        while (queue.isNotEmpty()) {

            val size = queue.size

            for (i in 0 until size) {

                val current = queue.removeFirst()
                result[current.first][current.second] = dist

                if (current.second - 1 >= 0 && visited[current.first][current.second - 1] == 0) {
                    visited[current.first][current.second - 1] = 1
                    queue.addLast(Pair(current.first, current.second - 1))
                }

                if (current.second + 1 < mat[0].size && visited[current.first][current.second + 1] == 0) {
                    visited[current.first][current.second + 1] = 1
                    queue.addLast(Pair(current.first, current.second + 1))
                }

                if (current.first - 1 >= 0 && visited[current.first - 1][current.second] == 0) {
                    visited[current.first - 1][current.second] = 1
                    queue.addLast(Pair(current.first - 1, current.second))
                }

                if (current.first + 1 < mat.size && visited[current.first + 1][current.second] == 0) {
                    visited[current.first + 1][current.second] = 1
                    queue.addLast(Pair(current.first + 1, current.second))
                }
            }
            dist++
        }


        return result
    }

}