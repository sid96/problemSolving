import java.util.PriorityQueue

fun main() {
    println(LeetCode2462Solution().totalCost(intArrayOf(1,2,4,1), 3, 1))
}



class LeetCode2462Solution {

    fun totalCost(costs: IntArray, k: Int, candidates: Int): Long {
        var totalCost = 0L
        val n = costs.size
        val pqLeft = PriorityQueue<Int>()
        val pqRight = PriorityQueue<Int>()

        var indexLeftEnd = candidates - 1

        var indexRightStart = maxOf(candidates, n - candidates)

        for(i in 0..indexLeftEnd) {
            pqLeft.add(costs[i])
        }

        for(i in indexRightStart until n) {
            pqRight.add(costs[i])
        }

        for(j in 1..k) {

            val minLeft = if (pqLeft.isEmpty().not()) pqLeft.peek() else Int.MAX_VALUE
            val minRight =  if (pqRight.isEmpty().not()) pqRight.peek() else Int.MAX_VALUE

            if(minLeft <= minRight) {
                totalCost += minLeft
                pqLeft.remove()

                if(indexLeftEnd < indexRightStart - 1) {
                    indexLeftEnd++
                    pqLeft.add(costs[indexLeftEnd])
                }

            } else {
                totalCost += minRight
                pqRight.remove()

                if(indexRightStart > indexLeftEnd + 1) {
                    indexRightStart--
                    pqRight.add(costs[indexRightStart])
                }
            }

        }

        return totalCost
    }

}