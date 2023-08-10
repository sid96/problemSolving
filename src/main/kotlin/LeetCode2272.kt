fun main() {
    println(LeetCode2272Solution().largestVariance("lripaa"))
}


internal class LeetCode2272Solution {
    fun largestVariance(s: String): Int {
        val counter = IntArray(26)
        for (ch in s.toCharArray()) {
            counter[(ch.code - 'a'.code)]++
        }
        var globalMax = 0
        for (i in 0..25) {
            for (j in 0..25) {
                // major and minor cannot be the same, and both must appear in s.
                if (i == j || counter[i] == 0 || counter[j] == 0) {
                    continue
                }

                // Find the maximum variance of major - minor.
                val major = ('a'.code + i).toChar()
                val minor = ('a'.code + j).toChar()
                var majorCount = 0
                var minorCount = 0

                var string = s

                // The remaining minor in the rest of s.
                // int restMinor = counter[j];
                for(l in 0..1) {
                    for (ch in string.toCharArray()) {
                        if (ch == major) {
                            majorCount++
                        }
                        if (ch == minor) {
                            minorCount++
                            // restMinor--;
                        }

                        // Only update the variance if there is at least one minor.
                        if (minorCount > 0 && majorCount > 0) globalMax = Math.max(globalMax, majorCount - minorCount)

                        // We can discard the previous string if there is at least one remaining minor.
                        if (majorCount < minorCount) {
                            majorCount = 0
                            minorCount = 0
                        }
                    }

                    string = s.reversed()
                }

            }
        }
        return globalMax
    }
}