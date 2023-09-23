fun main() {
    println(
        LeetCode1048Solution().longestStrChain(
            arrayOf("a", "b", "ba", "bca", "bda", "bdca")
        )
    )
}


// Time Complexity = O(N^2 . L)
// Space Complexity = O(N)

/*
Note - The time complexity can be optimized to O(L^2 . N) if we think about picking a word, deleting 1 char in each iteration and performing dfs if the
       new word is present in the list
*/
class LeetCode1048Solution {
    fun longestStrChain(words: Array<String>): Int {

        words.sortBy { word -> word.length }

        var result = 0
        val dp = IntArray(words.size) { -1 }

        for (i in words.indices) {
            result = maxOf(result, solve(i, words, dp))
        }

        return result
    }

    private fun solve(currentIndex: Int, words: Array<String>, dp: IntArray): Int {

        if (currentIndex >= words.size) return 0

        if (dp[currentIndex] != -1) {
            return dp[currentIndex]
        }

        var longestChainFromCurrent = 1

        for (i in currentIndex + 1 until words.size) {

            if (words[i].length - words[currentIndex].length == 1 && isSubs(words[currentIndex], words[i])) {

                longestChainFromCurrent = maxOf(longestChainFromCurrent, 1 + solve(i, words, dp))
            }

        }

        dp[currentIndex] = longestChainFromCurrent
        return longestChainFromCurrent
    }

    private fun isSubs(s1: String, s2: String): Boolean {
        var i = 0
        var j = 0
        while (i < s1.length) {
            if (j >= s2.length) return false
            if (s1[i] == s2[j]) {
                i++
            }
            j++
        }
        return true
    }
}