fun main() {
    print(Leetcode715Solution().minimumDeleteSum("delete", "leet"))
}

class Leetcode715Solution {
    fun minimumDeleteSum(s1: String, s2: String): Int {

        val dp = HashMap<Pair<Int, Int>, Int>()

        val maxLcsValue = LCS(s1, 0, s2, 0, dp)

        return sumOfChars(s1) - maxLcsValue + sumOfChars(s2) - maxLcsValue

    }

    //Length of Longest Common Subsequence
    fun LCS(s1: String, i: Int, s2: String, j: Int, dp: HashMap<Pair<Int, Int>, Int>): Int {

        if(dp.containsKey(Pair(i,j))) {
            return dp[Pair(i,j)]!!
        }

        if (i >= s1.length || j >= s2.length) {
            return 0
        }

        return if (s1[i] == s2[j]) {
            dp[Pair(i,j)] = s1[i].toInt() + LCS(s1, i + 1, s2, j + 1, dp)
            dp[Pair(i,j)]!!
        } else {
            dp[Pair(i,j)] = maxOf(LCS(s1, i, s2, j + 1, dp), LCS(s1, i + 1, s2, j, dp))
            dp[Pair(i,j)]!!
        }
    }

    fun sumOfChars(string: String): Int {
        var sum = 0
        string.forEach {
            sum += it.toInt()
        }
        return sum
    }
}