fun main() {
    println(
        LeetCode844Solution().backspaceCompare(
            "ab##",
            "c#d#"
        )
    )
}

class LeetCode844Solution {
    fun backspaceCompare(s: String, t: String): Boolean {
        var i = s.length - 1
        var j = t.length - 1

        while(i >= 0 && j >= 0) {
            val charAtI = s[i]
            val charAtJ = t[j]
            if(charAtI != '#' && charAtJ != '#') {
                if(charAtI == charAtJ) {
                    i--
                    j--
                } else {
                    return false
                }
            }

            if(i >= 0 && s[i] == '#') {
                var hashCountI = 0
                while(i >= 0) {
                    if(s[i] == '#') {
                        i--
                        hashCountI++
                    } else if(hashCountI > 0) {
                        i--
                        hashCountI--
                    } else {
                        break
                    }
                }
            }

            if(j >= 0 && t[j] == '#') {
                var hashCountJ = 0
                while(j >= 0) {
                    if(t[j] == '#') {
                        j--
                        hashCountJ++
                    } else if(hashCountJ > 0) {
                        j--
                        hashCountJ--
                    } else {
                        break
                    }
                }
            }
        }

        return i == j
    }

}