fun main() {

    println(
        LeetCode74Solution().searchMatrix(
            arrayOf(
                intArrayOf(1,3,5,7),
                intArrayOf(10,11,16,20),
                intArrayOf(23,30,34,60),
            ),
            target = 20
        )
    )
}


class LeetCode74Solution() {

    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {

        val row = findCorrectRow(0, matrix.size - 1, matrix, target)

        if(matrix[row][0] == target) return true

        return searchColumn(0, matrix[row].size - 1, matrix, target, if(row == 0 || matrix[row][0] <= target) row else row - 1)
    }

    fun findCorrectRow(s: Int, e: Int, matrix: Array<IntArray>, target: Int): Int {

        var start = s
        var end = e

        while(start < end) {

            val mid = (start + end) / 2

            if(target <= matrix[mid][0]) {
                end = mid
            } else {
                start = mid + 1
            }
        }

        return start

    }

    fun searchColumn(s: Int, e: Int, matrix: Array<IntArray>, target: Int, row: Int): Boolean {

        var start = s
        var end = e

        while(start < end) {

            val mid = (start + end) / 2

            if(target <= matrix[row][mid]) {
                end = mid
            } else {
                start = mid + 1
            }
        }

        return matrix[row][start] == target
    }
}