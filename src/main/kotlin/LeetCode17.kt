fun main() {

    println(
        LeetCode17Solution().letterCombinations(
            "23"
        )
    )
}


class LeetCode17Solution() {

    val result = ArrayList<String>()
    val phoneBook = arrayOf(
        "",
        "",
        "abc",
        "def",
        "ghi",
        "jkl",
        "mno",
        "pqrs",
        "tuv",
        "wxyz"
    )


    fun letterCombinations(digits: String): List<String> {

        if(digits.isNotEmpty()) {
            solve(digits, StringBuilder())
        }

        return result

    }

    private fun solve(digits: String, currentString: StringBuilder) {

        if(currentString.length == digits.length) {
            result.add(StringBuilder(currentString).toString())
            return
        }

        val letters = phoneBook[digits[currentString.length].toString().toInt()]

        letters.forEach {
            currentString.append(it)
            solve(digits, currentString)
            currentString.deleteCharAt(currentString.length - 1)
        }
    }
}