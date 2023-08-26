import java.util.*
import kotlin.collections.HashSet

fun main() {
    println(
        Leetcode934Solution().shortestBridge(
            arrayOf(
                intArrayOf(1,1,0,0,0),
                intArrayOf(1,0,0,0,0),
                intArrayOf(1,0,0,0,0),
                intArrayOf(0,0,0,1,1),
                intArrayOf(0,0,0,1,1),
            )
        )
    )
}

class Leetcode934Solution {
    fun shortestBridge(grid: Array<IntArray>): Int {

        var result = Int.MAX_VALUE

        outer@ for(row in grid.indices) {
            for(column in 0 until grid[0].size) {
                if(grid[row][column] == 1) {
                    //mark one of the component as 2
                    markSecondComponent(row, column, grid)
                    break@outer
                }
            }
        }

        //perform bfs from every 2 through 0 until 1 to find min
        for(row in grid.indices) {
            for(column in 0 until grid[0].size) {
                if(grid[row][column] == 2) {
                    //mark one of the component as 2
                    result = minOf(result, bfs(row, column, grid))
                }
            }
        }


        return result


    }

    private fun bfs(startRow: Int, startCol: Int, grid: Array<IntArray>): Int {
        val source = Pair(startRow, startCol)
        val queue = LinkedList<Pair<Int, Int>>()
        val visited = HashSet<Pair<Int, Int>>()

        queue.addLast(source)
        visited.add(Pair(source.first, source.second))

        var distance = 0

        while(queue.isNotEmpty()) {

            val size = queue.size

            repeat(size) {
                val current = queue.removeFirst()

                if( current.first > 0 && grid[current.first - 1][current.second] == 1 ||
                    current.first < grid.size - 1 && grid[current.first + 1][current.second] == 1 ||
                    current.second > 0 && grid[current.first][current.second - 1] == 1 ||
                    current.second < grid[0].size - 1 && grid[current.first][current.second + 1] == 1
                ) {
                    return distance
                }

                //check for element towards the top
                if(current.first > 0 && visited.contains(Pair(current.first - 1, current.second)).not() && grid[current.first - 1][current.second] == 0) {
                    visited.add(Pair(current.first - 1, current.second))
                    queue.addLast(Pair(current.first - 1, current.second))
                }

                //check for element towards the bottom
                if(current.first < grid.size - 1 && visited.contains(Pair(current.first +1, current.second)).not() && grid[current.first + 1][current.second] == 0) {
                    visited.add(Pair(current.first + 1, current.second))
                    queue.addLast(Pair(current.first + 1, current.second))
                }

                //check for element towards the left
                if(current.second > 0 && visited.contains(Pair(current.first, current.second - 1)).not() && grid[current.first][current.second - 1] == 0) {
                    visited.add(Pair(current.first, current.second - 1))
                    queue.addLast(Pair(current.first, current.second - 1))
                }

                //check for element towards the right
                if(current.second < grid[0].size - 1 && visited.contains(Pair(current.first, current.second + 1)).not() && grid[current.first][current.second + 1] == 0) {
                    visited.add(Pair(current.first, current.second + 1))
                    queue.addLast(Pair(current.first, current.second + 1))
                }
            }

            distance++

        }

        return Int.MAX_VALUE
    }

    private fun markSecondComponent(startRow: Int, startCol: Int, grid: Array<IntArray>) {

        val source = Pair(startRow, startCol)
        val queue = LinkedList<Pair<Int, Int>>()

        queue.addLast(source)
        grid[source.first][source.second] = 2

        while(queue.isNotEmpty()) {

            val current = queue.removeFirst()

            //check for element towards the top
            if(current.first > 0 && grid[current.first - 1][current.second] == 1) {
                grid[current.first - 1][current.second] = 2
                queue.addLast(Pair(current.first - 1, current.second))
            }

            //check for element towards the bottom
            if(current.first < grid.size - 1 && grid[current.first + 1][current.second] == 1) {
                grid[current.first + 1][current.second] = 2
                queue.addLast(Pair(current.first + 1, current.second))
            }

            //check for element towards the left
            if(current.second > 0 && grid[current.first][current.second - 1] == 1) {
                grid[current.first][current.second - 1] = 2
                queue.addLast(Pair(current.first, current.second - 1))
            }

            //check for element towards the right
            if(current.second < grid[0].size - 1 && grid[current.first][current.second + 1] == 1) {
                grid[current.first][current.second + 1] = 2
                queue.addLast(Pair(current.first, current.second + 1))
            }
        }
    }
}