fun main() {
    println(
        LeetCode168Solution().convertToTitle(
            731
        )
    )
}

class LeetCode168Solution {
    fun convertToTitle(columnNumber: Int): String {

        var result = ""
        var num = columnNumber

        while (num != 0) {

            val remainder = (num - 1) % 26

            num -= 1
            num /= 26


            result = "${(remainder + 65).toChar()}$result"

        }

        return result

    }

}