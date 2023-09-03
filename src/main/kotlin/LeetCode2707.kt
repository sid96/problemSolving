fun main() {

    println(
        LeetCode2707Solution().minExtraChar(
            "abxybcaacd",
            arrayOf(
                "xyz",
                "bc",
                "ab",
                "a",
                "aacd",
            )
        )
    )
}


class LeetCode2707Solution {
    fun minExtraChar(s: String, dictionary: Array<String>): Int {

        val dict = HashSet<String>()

        dictionary.forEach { item ->
            dict.add(item)
        }

        val n = s.length

        val dp = IntArray(n + 1) { 0 }

        for (i in n - 1 downTo 0) {

            val sb = StringBuilder()
            var ans = Int.MAX_VALUE

            for (j in i until n) {

                val currentString = sb.append(s[j]).toString()

                val charLeft = if (dict.contains(currentString).not()) {
                    currentString.length + dp[j + 1]
                } else {
                    dp[j + 1]
                }

                ans = minOf(ans, charLeft)
            }

            dp[i] = ans

        }

        return dp[0]

    }
}