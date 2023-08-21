fun main() {
    println(LeetCode459Solution().repeatedSubstringPattern("abab"))
}


class LeetCode459Solution {
    fun repeatedSubstringPattern(s: String): Boolean {

        if (s.length == 1) return false

        var windowStart = 0
        var windowEnd = 1


        while (windowEnd < s.length) {
            if (s[windowStart] == s[windowEnd]) {

                windowStart++
                windowEnd++
            } else {
                val substringSizeTillNow = windowStart
                windowEnd = windowEnd - substringSizeTillNow + 1
                windowStart = 0

                while (windowEnd < s.length && s[windowEnd] != s[windowStart]) windowEnd++

            }
        }

        val lengthOfSubstring = windowEnd - windowStart

        return (s.length % lengthOfSubstring == 0 && s.length / lengthOfSubstring > 1)
    }

}