fun main() {

    println(
        LeetCode2007Solution().findOriginalArray(
            intArrayOf(1,3,4,2,6,8)
        )
    )
}


class LeetCode2007Solution {

    fun findOriginalArray(changed: IntArray): IntArray {

        changed.sort()

        val bsList = ArrayList<Int>()
        val original = arrayListOf<Int>()

        changed.forEach { item ->
            val isEven = item%2 == 0

            if(isEven) {
                val itemHalf = item/2

                val index = search(itemHalf, bsList)
                if(index != -1) {
                    original.add(itemHalf)
                    bsList.removeAt(index)
                } else {
                    bsList.add(item)
                }
            } else {
                bsList.add(item)
            }
        }

        return if(bsList.isEmpty()) {
            original.toIntArray()
        } else {
            intArrayOf()
        }
    }

    private fun search(item: Int, list: ArrayList<Int>): Int {
        val lastIndex = list.size - 1
        var start = 0
        var end = lastIndex

        while(start < end) {

            val mid = (start + end)/2

            if(item > list[mid]) {
                start = mid + 1
            } else {
                end = mid
            }
        }

        return if(list.isNotEmpty() && list[start] == item) start else -1

    }
}