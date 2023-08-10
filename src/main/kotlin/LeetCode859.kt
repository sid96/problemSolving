import java.util.Arrays.copyOf

fun main() {
    println(
        LeetCode859Solution().buddyStrings(
            "ab",
            "ba"
        )
    )
}


class LeetCode859Solution {

    fun countOccurrences(s: String, ch: Char): Int {
        return s.filter { it == ch }.count()
    }

    fun buddyStrings(s: String, goal: String): Boolean {

        if (s.length != goal.length) return false

        var i = 0
        while (i < s.length && s[i] == goal[i]) {
            i++
        }

        if (i == s.length) {

            for (k in 0 until s.length) {
                if (countOccurrences(s, s[k]) >= 2) {
                    return true
                }
            }

            return false
        } else {

            var j = i + 1
            while (j < goal.length && goal[j] == s[j]) {
                j++
            }

            if(j == goal.length) return false

            val sCharArray: CharArray = s.toCharArray()
            swap(sCharArray, i, j)


            return goal.equals(String(sCharArray))
        }
    }

    fun swap(s: CharArray, i: Int, j: Int) {
        val temp = s[i]
        s[i] = s[j]
        s[j] = temp
    }
}