fun main() {
    print(Leetcode13375Solution().kWeakestRows(
        arrayOf(
            intArrayOf(1,1,0,0,0),
            intArrayOf(1,1,1,1,0),
            intArrayOf(1,0,0,0,0),
            intArrayOf(1,1,0,0,0),
            intArrayOf(1,1,1,1,1)
        ),
        3
    )
    )
}

class Leetcode13375Solution {
    fun kWeakestRows(mat: Array<IntArray>, k: Int): IntArray {

        val soldierList = arrayListOf<Pair<Int, Int>>()

        for(i in 0 until mat.size) {

            var j = 0

            while(j < mat[0].size && mat[i][j] == 1) {
                j++
            }

            soldierList.add(Pair(i, j))

        }

        soldierList.sortBy { item ->
            item.second
        }

        val result = arrayListOf<Int>()

        soldierList.forEachIndexed { index, item ->
            if(index < k) {
                result.add(item.first)
            } else {
                return@forEachIndexed
            }
        }

        return result.toIntArray()
    }
}