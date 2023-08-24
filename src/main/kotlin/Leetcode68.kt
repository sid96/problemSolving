fun main() {
    println(
        Leetcode68Solution().fullJustify(
            arrayOf(
                "This",
                "is",
                "an",
                "example",
                "of",
                "text",
                "justification."
            ),
            16
        )
    )
}

class Leetcode68Solution {

    fun fullJustify(words: Array<String>, maxWidth: Int): List<String> {

        val result = arrayListOf<String>()
        var sentenceBuilder = StringBuilder()
        var index = 0   //index of each word

        while (index < words.size) {

            val currentWord = words[index]

            if (sentenceBuilder.length == maxWidth) {
                //the length of line  is equal to the maxWidth and no extra spaces left to be distributed
                result.add(sentenceBuilder.toString())
                sentenceBuilder = StringBuilder()
            }

            if (sentenceBuilder.isNotEmpty()) {
                //line length is less than maxWidth so a space needs to be added before adding the next word to the line
                sentenceBuilder.append(" ")
            }

            if (currentWord.length <= maxWidth - sentenceBuilder.length) {
                //possible to add the next word to the line so add it.
                sentenceBuilder.append(currentWord)
                index++
            } else {
                //next word cannot be added so proceed to divide the remaining spaces, add it to the result and create a new builder to start tracking the next line
                sentenceBuilder = divideRemainingSpaces(sentenceBuilder, maxWidth - sentenceBuilder.length)

                result.add(sentenceBuilder.toString())

                sentenceBuilder = StringBuilder()
            }

        }


        //Add the last line to the result with extra spaces if present
        if (sentenceBuilder.isNotEmpty()) {
            val remainingSpaces = maxWidth - sentenceBuilder.length

            repeat(remainingSpaces) {
                sentenceBuilder.append(" ")
            }

            result.add(sentenceBuilder.toString())
        }

        return result

    }

    private fun divideRemainingSpaces(sentenceBuilder: StringBuilder, remainingSpacesCount: Int): StringBuilder {

        val wordsListSb = arrayListOf<StringBuilder>()
        val wordsListString = sentenceBuilder.trim().split(" ")

        wordsListString.forEach { word ->
            wordsListSb.add(StringBuilder(word))
        }

        val totalSpacesCount = remainingSpacesCount + wordsListSb.size


        if (wordsListSb.size > 1) {
            val commonSpaces = totalSpacesCount / (wordsListSb.size - 1)

            for (index in 0 until wordsListSb.size - 1) {
                repeat(commonSpaces) {
                    wordsListSb[index].append(" ")
                }
            }
        }

        var remainingSpaces = if (wordsListSb.size > 1) {
            totalSpacesCount % (wordsListSb.size - 1)
        } else {
            totalSpacesCount
        }

        var index = 0

        while (true) {

            if (remainingSpaces == 0) break

            if (index >= wordsListSb.size - 1) index = 0

            wordsListSb[index].append(" ")

            remainingSpaces--

            index++
        }

        val resultSB = StringBuilder()

        wordsListSb.forEach { word ->
            resultSB.append(word.toString())
        }

        return resultSB
    }

}