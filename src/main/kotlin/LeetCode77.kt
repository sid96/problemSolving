import java.util.Arrays.copyOf
import java.util.LinkedList

fun main() {

    println(
        LeetCode77Solution().combine(
            1,1
        )
    )
}


class LeetCode77Solution() {

    val result = ArrayList<ArrayList<Int>>()

    fun combine(n: Int, k: Int): List<List<Int>> {

        solve(1, n, k, ArrayList())

        return result
    }

    fun solve(current: Int, n: Int, k: Int, currentList: ArrayList<Int>) {

        if(currentList.size == k) {
            result.add(arrayListOf<Int>().apply { addAll(currentList) })
            return
        }

        if(current > n) return

        currentList.add(current)

        solve(current + 1, n, k, currentList)

        currentList.remove(current)

        solve(current + 1, n, k, currentList)

    }
}