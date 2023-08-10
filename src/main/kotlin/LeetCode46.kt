import java.util.Arrays.copyOf
import java.util.LinkedList

fun main() {

    println(
        LeetCode46Solution().permute(
            intArrayOf(1,2,3)
        )
    )
}


class LeetCode46Solution() {

    val result = ArrayList<List<Int>>()

    fun permute(nums: IntArray): List<List<Int>> {

        val map = LinkedHashSet<Int>()

        solve(nums, map)

        return result

    }

    fun solve(nums: IntArray, map: LinkedHashSet<Int>) {

        if(map.size == nums.size) {
            storeResult(map)
            return
        }


        for(i in nums) {
            if(map.contains(i).not()) {
                map.add(i)
                solve(nums, map)
                map.remove(i)
            }
        }
    }

    fun storeResult(map: LinkedHashSet<Int>) {

        val list = arrayListOf<Int>()
        map.forEach {
            list.add(it)
        }

        result.add(list)

    }
}