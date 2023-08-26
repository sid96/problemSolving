import java.util.*
import kotlin.collections.HashSet

fun main() {
    println(
        LeetCode365Solution().canMeasureWater(
            3,
            5,
            4
        )
    )
}

class LeetCode365Solution {
    fun canMeasureWater(jug1Capacity: Int, jug2Capacity: Int, targetCapacity: Int): Boolean {

        if (jug1Capacity == targetCapacity || jug2Capacity == targetCapacity) return true

        val totalPossibleCapacity = jug1Capacity + jug2Capacity
        if (totalPossibleCapacity < targetCapacity) return false

        return bfs(
            jug1Capacity,
            jug2Capacity,
            targetCapacity
        )
    }

    private fun bfs(jug1Capacity: Int, jug2Capacity: Int, targetCapacity: Int): Boolean {

        val queue = LinkedList<Pair<Int, Int>>()
        val visited = HashSet<Pair<Int, Int>>()

        val firstState = Pair(0, 0)

        queue.addLast(firstState)
        visited.add(firstState)


        while (queue.isNotEmpty()) {

            val current = queue.removeFirst()
            val totalCurrentCapacity = current.first + current.second

            if (totalCurrentCapacity == targetCapacity) return true

            if (current.first < jug1Capacity && visited.contains(Pair(jug1Capacity, current.second)).not()) {
                val nextState = Pair(jug1Capacity, current.second)
                visited.add(nextState)
                queue.addLast(nextState)
            }

            if (current.second < jug2Capacity && visited.contains(Pair(current.first, jug2Capacity)).not()) {
                val nextState = Pair(current.first, jug2Capacity)
                visited.add(nextState)
                queue.addLast(nextState)
            }

            if (current.first > 0 && visited.contains(Pair(0, current.second)).not()) {
                val nextState = Pair(0, current.second)
                visited.add(nextState)
                queue.addLast(nextState)
            }

            if (current.second > 0 && visited.contains(Pair(current.first, 0)).not()) {
                val nextState = Pair(current.first, 0)
                visited.add(nextState)
                queue.addLast(nextState)
            }

            val remainingJug2Cap = jug2Capacity - current.second

            if (remainingJug2Cap > 0) {
                val capacityToPour = minOf(current.first, remainingJug2Cap)
                val nextState = Pair(current.first - capacityToPour, current.second + capacityToPour)

                if (visited.contains(Pair(nextState.first, nextState.second)).not()) {
                    visited.add(nextState)
                    queue.addLast(nextState)
                }

            }

            val remainingJug1Cap = jug1Capacity - current.first

            if (remainingJug1Cap > 0) {
                val capacityToPour = minOf(current.second, remainingJug1Cap)
                val nextState = Pair(current.first + capacityToPour, current.second - capacityToPour)

                if (visited.contains(Pair(nextState.first, nextState.second)).not()) {
                    visited.add(nextState)
                    queue.addLast(nextState)
                }
            }

        }

        return false
    }
}