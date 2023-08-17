fun main() {
    val result = LeetCode239Solution().maxSlidingWindow(
        nums = intArrayOf(1),
        k = 1
    )

    result.forEach {
        print("$it ")
    }
}

class LeetCode239Solution {

    fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {

        val result = ArrayList<Int>()
        val window = ArrayList<Int>()

        for (index in 0 until k) {
            window.add(nums[index])
        }

        window.sort()

        var start = 0
        var end = start + k - 1

        while (end < nums.size - 1) {

            result.add(window[k - 1])

            val removeIndex = findElementIndexInWindow(window, nums[start])
            window.removeAt(removeIndex)

            val addIndex = findElementIndexInWindow(window, nums[end + 1])
            if (addIndex == window.size - 1) {
                if (nums[end + 1] > window[addIndex]) {
                    window.add(nums[end + 1])
                } else {
                    window.add(addIndex, nums[end + 1])
                }
            } else {
                window.add(addIndex, nums[end + 1])
            }

            start++
            end++
        }

        result.add(window[k - 1])

        return result.toIntArray()
    }

    private fun findElementIndexInWindow(window: ArrayList<Int>, element: Int): Int {

        var start = 0
        var end = window.size - 1

        while (start < end) {

            val mid = (start + end) / 2

            if (element <= window[mid]) {
                end = mid
            } else {
                start = mid + 1
            }
        }

        return start
    }


}