fun main() {

    println(
        LeetCode767Solution().reorganizeString(
            "abcc"
        )
    )
}

//This is the solution that I could come up with however this is not the best solution. However since the constraints are small hence this is getting submitted. T.C = O(n^2)
//The correct solution would be using the HashMap and a Priority Queue. Expected T.C is O(nlogk) or O(n)



/*The main concept is that if the frequency of the most occurring element is > (s.length + 1)/2 then we cannot place the elements such that no 2 same elements are besides each other*/

class LeetCode767Solution {
    fun reorganizeString(s: String): String {

        if (s.length == 1) return s

        val charArray = s.toMutableList()

        for (index in 0 until charArray.size - 1) {

            if (charArray[index] == charArray[index + 1]) {

                val validCharIndex = findNextValidChar(charArray, index)

                if (validCharIndex == -1) {
                    return ""
                } else {
                    insert(charArray, index, validCharIndex)
                }

            }

        }

        return charArray. joinToString(separator = "")
    }

    private fun insert(charArray: MutableList<Char>, index: Int, validCharIndex: Int) {
        val charToAdd = charArray[validCharIndex]
        charArray.removeAt(validCharIndex)

        if (index < validCharIndex) {
            charArray.add(index + 1, charToAdd)
        } else {
            charArray.add(index, charToAdd)
        }

    }

    private fun findNextValidChar(charArray: MutableList<Char>, currentIndex: Int): Int {
        for (index in charArray.indices) {
            if (index == 0) {
                if (charArray[index] != charArray[currentIndex]) {
                    return 0
                }
            } else if (index == charArray.size - 1) {
                if (charArray[index] != charArray[currentIndex]) {
                    return index
                }
            } else {
                if (charArray[index - 1] != charArray[index + 1] && charArray[index] != charArray[currentIndex]) {
                    return index
                }
            }
        }
        return -1
    }
}