import java.util.Arrays.copyOf

fun main() {
    println(
        LeetCode2305Solution().distributeCookies(
            arrayListOf(6,1,3,2,2,4,1,2).toIntArray(),
            3,
        )
    )
}


class LeetCode2305Solution {

    fun distributeCookies(cookies: IntArray, k: Int): Int {

        val students = Array(k) { 0 }

        return solve(0, cookies, students)

    }

    fun solve(index: Int, cookies: IntArray, students: Array<Int>): Int {
        if (index == cookies.size) {
            return findUnfairness(students)
        }

        var min = Int.MAX_VALUE

        for (i in students.indices) {
            val studentCopy = copyOf(students, students.size)
            studentCopy[i] += cookies[index]
            val result = solve(index + 1, cookies, studentCopy)
            min = minOf(min, result)
        }

        return min
    }

    fun findUnfairness(students: Array<Int>): Int {
        var max = Int.MIN_VALUE
        students.forEach {
            if (it > max) max = it
        }

        return max
    }
}