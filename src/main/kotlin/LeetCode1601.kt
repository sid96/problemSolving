import java.util.Arrays.copyOf

fun main() {
    println(
        LeetCode1601Solution().maximumRequests(
            7,
            arrayOf(
                intArrayOf(0, 1),
                intArrayOf(1, 2),
                intArrayOf(2, 3),
                intArrayOf(3, 0),
                intArrayOf(4, 2),
                intArrayOf(3, 5),
                intArrayOf(5, 6),
                intArrayOf(6, 4)
            )
        )
    )
}


class LeetCode1601Solution {

    var maxCycle = ArrayList<Pair<Int, Int>>()

    fun maximumRequests(n: Int, requests: Array<IntArray>): Int {

        var result = 0

        val grid = Array(n) { IntArray(n) { 0 } }

        val outgoingEdges = IntArray(n) { 0 }

        for (i in requests.indices) {

            outgoingEdges[requests[i][0]]++

            if(requests[i][0] == requests[i][1])  {
                result ++
            } else {
                grid[requests[i][0]][requests[i][1]]++
            }
        }

        for (buildingNum in 0 until n) {

            while(outgoingEdges[buildingNum] > 0) {

                outgoingEdges[buildingNum] = outgoingEdges[buildingNum] - 1

                findLongestCycle(buildingNum, grid)

                result += maxCycle.size

                removeEdgesFromGrid(grid)

            }

        }


        return result

    }

    fun findLongestCycle(buildingNum: Int, grid: Array<IntArray>) {

        maxCycle = ArrayList()

        val cycle = ArrayList<Pair<Int, Int>>()

        solve(buildingNum, buildingNum, grid, cycle)

    }


    fun solve(current: Int, source: Int, grid: Array<IntArray>, cycle: ArrayList<Pair<Int, Int>>) {

        for (i in grid.indices) {
            if (grid[current][i] > 0) {

                cycle.add(Pair(current, i))
                grid[current][i]--

                if (i == source) {

                    if (cycle.size > maxCycle.size) {
                        maxCycle = ArrayList(cycle)
                    }

                    grid[current][i]++
                    cycle.removeLast()
                    continue
                }

                solve(i, source, grid, cycle)

                grid[current][i]++
                cycle.removeLast()

            }
        }
    }

    fun removeEdgesFromGrid(grid: Array<IntArray>) {
        maxCycle.forEach {
            grid[it.first][it.second] = grid[it.first][it.second] - 1
        }
    }
}