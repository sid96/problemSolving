fun main() {

    println(
        LeetCode139Solution().wordBreak(
            "catsandog",
            arrayListOf("cats","dog","sand","and","cat","an")
        )
    )
}


class LeetCode139Solution() {
    
    fun wordBreak(s: String, wordDict: List<String>): Boolean {

        val dp = HashMap<Int, Boolean>()

        val wordMap = HashSet<String>()

        wordDict.forEach {
            wordMap.add(it)
        }

        return solve(s, wordMap, 0, dp)
    }

    private fun solve(s: String, wordMap: HashSet<String>, start: Int, dp: HashMap<Int, Boolean>): Boolean {

        if(start == s.length) return true

        val subString = StringBuilder()

        for(i in start until  s.length) {
            subString.append(s[i])

            if(wordMap.contains(subString.toString())) {

                val result = if(dp.containsKey(i+1)) {
                    dp[i+1]!!
                } else {
                    dp[i+1] = solve(s, wordMap, i + 1, dp)
                    dp[i+1]!!
                }

                if(result) {
                    return true
                }
            }
        }

        dp[start] = false
        return dp[start]!!

    }
}