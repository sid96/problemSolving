import com.sun.source.tree.DoWhileLoopTree
import java.util.Collections
import java.util.PriorityQueue

fun main() {
    println(
        LeetCode1514Solution().maxProbability(
            3,
            arrayOf(
                intArrayOf(0, 1),
                intArrayOf(1, 2),
                intArrayOf(0, 2)
            ),
            doubleArrayOf(0.5, 0.5, 0.3),
            0,
            2
        )
    )
}


class LeetCode1514Solution {

    class Edge(
        val start: Int,
        val end: Int,
        val weight: Double
    )

    fun maxProbability(n: Int, edges: Array<IntArray>, succProb: DoubleArray, start: Int, end: Int): Double {

        val adjList = ArrayList<ArrayList<Edge>>()

        for (i in 0 until n) {
            adjList.add(arrayListOf())
        }

        for (i in edges.indices) {
            adjList[edges[i][0]].add(Edge(edges[i][0], edges[i][1], -succProb[i]))
            adjList[edges[i][1]].add(Edge(edges[i][1], edges[i][0], -succProb[i]))
        }

        return findShortestPath(adjList, start, end)

    }

    private fun findShortestPath(
        adjList: ArrayList<ArrayList<Edge>>,
        start: Int,
        end: Int
    ): Double {
        val visited = BooleanArray(adjList.size)
        val distance = hashMapOf<Int, Double>()
        val pq = PriorityQueue<Pair<Int, Double>> { p1, p2 ->
            if(p1.second < p2.second) -1
            else if(p1.second > p2.second) 1
            else 0
        }

        pq.add(Pair(start, 1.0))
        distance[start] = 1.0

        while (pq.isEmpty().not()) {

            val minValueIndex = pq.remove()

            val edges = adjList[minValueIndex.first]

            for (i in edges.indices) {
                if (visited[edges[i].end].not()) {
                    val minTillNow = distance[edges[i].end] ?: 1.0
                    var newDist = (distance[edges[i].start] ?: 1.0) * edges[i].weight

                    if(newDist > 0) newDist = -newDist

                    if(newDist < minTillNow) {
                        distance[edges[i].end] = newDist
                        pq.add(Pair(edges[i].end, newDist))
                    }
                }
            }

            visited[minValueIndex.first] = true
        }

        return -(distance[end] ?: 0.0)
    }
}