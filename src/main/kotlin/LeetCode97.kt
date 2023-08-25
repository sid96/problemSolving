fun main() {
    println(
        LeetCode97Solution().isInterleave(
            "aabcc",
            "dbbca",
            "aadbbcbcac"
        )
    )
}


class LeetCode97Solution {

    fun isInterleave(s1: String, s2: String, s3: String): Boolean {

        if (s1.length + s2.length != s3.length) return false

        val dp = HashMap<Pair<Int, Int>, Boolean>()

        return solve(0, 0, s1, s2, s3, dp)

    }

    private fun solve(
        indexS1: Int,
        indexS3: Int,
        s1: String,
        s2: String,
        s3: String,
        dp: HashMap<Pair<Int, Int>, Boolean>
    ): Boolean {

        val indexS2 = indexS3 - indexS1

        if (indexS1 == s1.length && indexS2 == s2.length && indexS3 == s3.length) {
            return true
        }

        if (dp.containsKey(Pair(indexS1, indexS2))) {
            return dp[Pair(indexS1, indexS2)]!!
        }

        var takeS1Char = false
        var takeS2Char = false

        if (indexS1 < s1.length && s1[indexS1] == s3[indexS3]) {
            takeS1Char = solve(indexS1 + 1, indexS3 + 1, s1, s2, s3, dp)
        }

        if (indexS2 < s2.length && s2[indexS2] == s3[indexS3]) {
            takeS2Char = solve(indexS1, indexS3 + 1, s1, s2, s3, dp)
        }

        dp[Pair(indexS1, indexS2)] = takeS1Char || takeS2Char
        return dp[Pair(indexS1, indexS2)]!!

    }
}