fun main() {

    println(
        LeetCode31Solution().nextPermutation(
            intArrayOf(1,2,3)
        )
    )
}

// Current code written has the complexity of O(nlogn) but it can be optimised to O(n)
class LeetCode31Solution {

    fun nextPermutation(nums: IntArray) {


        var index = nums.size - 1
        while (index >= 0) {

            if(index > 0) {

                if(nums[index] <= nums[index - 1]) {
                    index--
                    continue
                } else {
                    break
                }

            } else {
                //if we have reached the last permutation
                return nums.sort()
            }

        }

        val start = index
        val end = nums.size - 1

        val nextGreaterIndex = findNextGreaterIndex(nums[index - 1], nums, start, end)

        val temp = nums[nextGreaterIndex]
        nums[nextGreaterIndex] = nums[index - 1]
        nums[index - 1] = temp

        nums.sort(start, end + 1)

    }

    private fun findNextGreaterIndex(element: Int, nums: IntArray, start: Int, end: Int): Int {

        var nextGreater = nums[start]
        var result = start
        for(index in start..end) {

            if(nums[index] in (element + 1) until nextGreater) {
                nextGreater = nums[index]
                result = index
            }

        }

        return result

    }
}