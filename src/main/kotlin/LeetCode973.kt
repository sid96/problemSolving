import java.util.*

fun main() {
    println(
        LeetCode973Solution().kClosest(
            arrayOf(
                intArrayOf(1,3),
                intArrayOf(2,2),
                intArrayOf(5,-1)
            ),
            2
        )
    )
}


class LeetCode973Solution {

    data class Point(
        val x: Int,
        val y: Int,
        val distance: Int
    )
    fun kClosest(points: Array<IntArray>, k: Int): Array<IntArray> {

        val priorityQueue = PriorityQueue<Point>() { p1, p2 ->
            p2.distance - p1.distance
        }

        points.forEach { point ->
            if(priorityQueue.size < k) {
                priorityQueue.add(Point(point[0], point[1], getDistance(point)))
            } else {
                val farthestPoint = priorityQueue.poll()
                val currentPoint = Point(point[0], point[1], getDistance(point))

                if(currentPoint.distance < farthestPoint.distance) {
                    priorityQueue.add(currentPoint)
                } else {
                    priorityQueue.add(farthestPoint)
                }
            }
        }

        val result = Array(priorityQueue.size) { IntArray(2) { 0 }}

        priorityQueue.forEachIndexed { index, point ->
            result[index][0] = point.x
            result[index][1] = point.y
        }

        return result
    }

    fun getDistance(point: IntArray): Int {
        return point[0] * point[0] + point[1] * point[1]
    }
}