fun main() {

    print(
        LeetCode1136Solution().minimumSemesters(
            3,
            arrayOf(
                intArrayOf(1, 2),
                intArrayOf(2, 3),
                intArrayOf(3, 1),
            )
        )
    )

}


class LeetCode1136Solution {

    fun minimumSemesters(n: Int, relations: Array<IntArray>): Int {

        val adjList = Array<ArrayList<Int>>(n) { arrayListOf() }
        val visited = IntArray(n) { -1 }

        relations.forEach { relation ->
            adjList[relation[0] - 1].add(relation[1] - 1)
        }

        var result = 0

        for (currentCourse in 0 until n) {

            val currentStack = BooleanArray(n) { false }

            result = maxOf(result, dfs(currentCourse, adjList, visited, currentStack))

            if(result == Int.MAX_VALUE) return -1
        }

        return result
    }

    private fun dfs(
        currentCourse: Int,
        adjList: Array<ArrayList<Int>>,
        visited: IntArray,
        currentStack: BooleanArray
    ): Int {

        if (currentStack[currentCourse]) {
            return Int.MAX_VALUE
        }

        if (visited[currentCourse] != -1) return visited[currentCourse]

        currentStack[currentCourse] = true

        var depth = 1

        for (nextCourse in adjList[currentCourse]) {
            val childNodes = dfs(nextCourse, adjList, visited, currentStack)

            if(childNodes == Int.MAX_VALUE) return Int.MAX_VALUE

            depth = maxOf(depth, 1 + childNodes)
        }

        visited[currentCourse] = depth

        currentStack[currentCourse] = false

        return depth
    }
}