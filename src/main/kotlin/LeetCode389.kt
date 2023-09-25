fun main() {
    println(
        LeetCode389Solution().findTheDifference("abcdefgh", "abcdefghi")
    )
}


class LeetCode389Solution {
    fun findTheDifference(s: String, t: String): Char {

        val freqArr = Array<Int>(26) { 0 }

        s.forEach { char ->
            freqArr[char.code - 97]++
        }

        t.forEach{char ->
            freqArr[char.code - 97]--

            if(freqArr[char.code - 97] < 0) {
                return char
            }
        }

        return ' '
    }
}