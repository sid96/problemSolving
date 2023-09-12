fun main() {
    println(
        Leetcode1647Solution().minDeletions(
            "aabbcdsabffew"
        )
    )
}

class Leetcode1647Solution {

    fun minDeletions(s: String): Int {

        var result = 0
        val n = s.length
        val freq = IntArray(26){ 0 }

        for(index in 0 until n) {
            freq[s[index].code - 97]++
        }

        freq.sort()

        var index = 25

        while(index > 0) {

            if(freq[index] > 0) {

                if(freq[index] <= freq[index - 1]) {
                    val charactersToRemove = freq[index - 1] - freq[index] + 1
                    result += charactersToRemove
                    freq[index - 1] = freq[index - 1] - charactersToRemove
                }

                index--

            } else {
                while(index >= 0) {
                    val charactersToRemove = freq[index]
                    result += charactersToRemove
                    index--
                }
            }
        }

        return result
    }
}