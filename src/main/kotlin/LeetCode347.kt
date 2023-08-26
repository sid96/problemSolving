import java.util.*
import kotlin.collections.HashMap

fun main() {
    println(
        LeetCode347Solution().topKFrequent(
            intArrayOf(1, 1, 1, 2, 2, 3),
            2
        )
    )
}

class LeetCode347Solution {
    data class NumFreqPair(
        val num: Int,
        val freq: Int
    )

    fun topKFrequent(nums: IntArray, k: Int): IntArray {

        val freq = HashMap<Int, Int>()

        nums.forEach { num ->
            freq[num] = (freq[num] ?: 0) + 1
        }

        val pq = PriorityQueue<NumFreqPair> { p1, p2 ->
            p1.freq - p2.freq
        }

        freq.forEach {


            val pqSize = pq.size

            if (pqSize < k) {
                pq.add(NumFreqPair(it.key, it.value))
            } else {
                pq.add(NumFreqPair(it.key, it.value))
                pq.poll()
            }

        }


        val result = arrayListOf<Int>()

        pq.forEach { ele ->
            result.add(ele.num)
        }

        return result.toIntArray()

    }

}