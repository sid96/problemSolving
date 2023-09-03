fun main() {

    println(
        LeetCode338Solution().countBits(
            20
        )
    )
}


class LeetCode338Solution {

    fun countBits(n: Int): IntArray {

        val resultList = IntArray(n+1) { 0 }
        var sizePointer = 1

        while(sizePointer < n + 1) {
            val temp = sizePointer
            for(i in 0 until temp) {
                sizePointer++
                resultList[sizePointer - 1] = (resultList[i] + 1)

                if(sizePointer == n + 1) return resultList
            }
        }

        return resultList

    }
}