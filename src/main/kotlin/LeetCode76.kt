fun main() {

    println(
        LeetCode76Solution().minWindow(
            "A","A"
        )
    )
}


class LeetCode76Solution {
    var result = ""
    fun minWindow(s: String, t: String): String {

        if(t.length > s.length) return ""

        val freqT = HashMap<Char, Int>()

        t.forEach { char ->
            freqT[char] = (freqT[char] ?: 0) + 1
        }

        var left = 0
        var right = s.length

        while(left <= right) {

            val mid = (left + right) / 2

            if(findFreqOfSubstring(s, mid, freqT, t.length)) {
                right = mid - 1
            } else {
                left = mid + 1
            }
        }

        return result
    }

    fun findFreqOfSubstring(s: String, size: Int, freqT: HashMap<Char, Int>, targetLength: Int) : Boolean {

        if(size < targetLength) return false

        val freqS = HashMap<Char, Int>()

        for(i in 0 until size) {
            freqS[s[i]] = (freqS[s[i]] ?: 0) + 1
        }

        var index = 0

        do {

            if(validWindow(freqS, freqT).not()) {
                index++

                if(freqS.containsKey(s[index - 1])) {
                    freqS[s[index - 1]] = freqS[s[index - 1]]!! - 1

                    if(freqS[s[index - 1]]!! == 0) {
                        freqS.remove(s[index - 1])
                    }
                }

                val nextCharIndex = index + size - 1

                if(nextCharIndex < s.length) {
                    freqS[s[nextCharIndex]] = (freqS[s[nextCharIndex]] ?: 0) + 1
                }
            } else {
                result = s.substring(index, index + size)
                return true
            }
        } while(index <= (s.length - size))

        return false

    }


    private fun validWindow(freqS: HashMap<Char, Int>, freqT: HashMap<Char, Int>): Boolean {

        freqT.forEach { entry ->

            if(freqS.containsKey(entry.key).not() || (freqS[entry.key]!! >= freqT[entry.key]!!).not()) {
                return false
            }

        }

        return true

    }
}