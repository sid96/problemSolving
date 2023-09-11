fun main() {

    println(
        LeetCode1282Solution().groupThePeople(
            intArrayOf(3,3,3,3,1,3,3)
        )
    )
}


class LeetCode1282Solution {

    fun groupThePeople(groupSizes: IntArray): List<List<Int>> {

        val map = mutableMapOf<Int, MutableList<Int>>()

        val result = mutableListOf<List<Int>>()

        groupSizes.forEachIndexed { index, groupSize ->

            if(map.containsKey(groupSize).not()) {
                map.put(groupSize, mutableListOf())
            }

            map[groupSize]!!.add(index)

            if(map[groupSize]!!.size == groupSize) {
                result.add(ArrayList(map[groupSize]!!))
                map[groupSize]!!.clear()
            }
        }

        return result

    }
}