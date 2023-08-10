fun main() {
    println(LeetCode802Solution().eventualSafeNodes(
        arrayOf(
//            intArrayOf(1,2),
//            intArrayOf(2,3),
//            intArrayOf(5),
//            intArrayOf(0),
//            intArrayOf(5),
//            intArrayOf(),
//            intArrayOf(),
//            intArrayOf(1,2,3,4),
//            intArrayOf(1,2),
//            intArrayOf(3,4),
//            intArrayOf(0,4),
//            intArrayOf(),
            intArrayOf(1),
            intArrayOf(2),
            intArrayOf(1,3),
            intArrayOf(),
        )
    ))
}


internal class LeetCode802Solution {
    fun eventualSafeNodes(graph: Array<IntArray>): List<Int> {

        val visited = BooleanArray(graph.size) { false }
        val result = HashSet<Int>()
        val currentNodesInPath = HashSet<Int>()

        for(i in graph.indices) {

            if(visited[i].not()) {
                dfs(i, visited, graph, currentNodesInPath, result)
            }

        }

        return result.sorted()

    }

    fun dfs(current: Int, visited: BooleanArray, graph: Array<IntArray>, currentNodesInPath: HashSet<Int>, result: HashSet<Int>): Boolean {

        var addToResult = true

        if(currentNodesInPath.contains(current)) return false

        currentNodesInPath.add(current)

        for(adj in graph[current]) {

            if(visited[adj]) {
                if(result.contains(adj).not()) {
                    addToResult = false
                }
            } else if(dfs(adj, visited, graph, currentNodesInPath, result).not()){
                addToResult = false
            }

        }

        visited[current] = true
        currentNodesInPath.remove(current)

        if(addToResult) result.add(current)

        return addToResult

    }
}