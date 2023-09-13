fun main() {
    println(
        Leetcode135Solution().candy(
            intArrayOf(10,30,40,39,39,38,37,39)
        )
    )
}

class Leetcode135Solution {

    fun candy(ratings: IntArray): Int {

        val result = Array(ratings.size) { 0 }

        val greaterThanNext = Array(ratings.size) { 0 }

        for(i in ratings.size - 2 downTo 0) {
            if(ratings[i+1] < ratings[i]) {
                greaterThanNext[i] = 1 + greaterThanNext[i + 1]
            } else {
                greaterThanNext[i] = 0
            }
        }

        var index = 0

        while(index < ratings.size) {
            if(index == 0) {
                result[index] = greaterThanNext[index] + 1
            } else {
                if(ratings[index] > ratings[index - 1]) {
                    result[index] = maxOf(result[index - 1] + 1, greaterThanNext[index] + 1)
                } else {
                    result[index] = maxOf(1, greaterThanNext[index] + 1)
                }
            }

            index++
        }

        var min = 0

        result.forEach {
            min += it
        }

        return min

    }

}