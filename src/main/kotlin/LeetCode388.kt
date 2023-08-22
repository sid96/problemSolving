fun main() {
    println(
//        LeetCode388Solution().lengthLongestPath(
//            "dir\\n\\tsubdir1\\n\\t\\tfile1.ext\\n\\t\\tsubsubdir1\\n\\tsubdir2\\n\\t\\tsubsubdir2\\n\\t\\t\\tfile2.ext"
//        )
        LeetCode388Solution().lengthLongestPath(
            "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"
        )
    )
}

class LeetCode388Solution {
    fun lengthLongestPath(input: String): Int {

        var result = 0
        val structureArray = input.split("\n") // [dir, \tsubdir1, \t\tfile1.txt, \t\tsubsubdir1, \tsubdir2, \t\tsubsubdir2, \t\t\tfile2.txt]

        for(i in structureArray.size - 1 downTo 0) {

            val currentItem = structureArray[i]

            if(currentItem.contains(".")) {
                //this is a file
                var tabCountFile = getTabCount(currentItem)
                var length = currentItem.length - tabCountFile
                var j = i-1

                while (--tabCountFile >= 0) {

                    while(structureArray[j].contains(".") || getTabCount(structureArray[j]) != tabCountFile) j--

                    val prevDirInStructure = structureArray[j]

                    length += prevDirInStructure.length + 1 - getTabCount(prevDirInStructure)
                }

                result = maxOf(result, length)

            }

        }

        return result
    }

    private fun getTabCount(currentItem: String): Int {
        return (currentItem.lastIndexOf("\t") + 1)
    }

}