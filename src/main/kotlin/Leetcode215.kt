import java.util.PriorityQueue

fun main() {
    println(
        Leetcode215Solution().findKthLargest(
            nums = intArrayOf(3,2,1,5,6,4),
            k = 1
        )
    )
}


class Leetcode215Solution {

    fun findKthLargest(nums: IntArray, k: Int): Int {

        val minPQ = PriorityQueue<Int> { a, b ->
            if (a < b) -1
            else if (a > b) 1
            else 0
        }

        nums.forEach {
            if (minPQ.size < k) {
                minPQ.add(it)
            } else {
                val minItem = minPQ.poll()
                if (it > minItem) {
                    minPQ.add(it)
                } else {
                    minPQ.add(minItem)
                }
            }
        }



        return minPQ.peek()
    }

}