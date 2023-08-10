import java.util.Arrays.copyOf
import java.util.LinkedList

fun main() {

    println(
        LeetCode1125Solution().smallestSufficientTeam(
            arrayOf("algorithms", "math", "java", "reactjs", "csharp", "aws"),
            arrayListOf(
                arrayListOf("algorithms", "math", "java"),
                arrayListOf("algorithms", "math", "reactjs"),
                arrayListOf("java", "csharp", "aws"),
                arrayListOf("reactjs", "csharp"),
                arrayListOf("csharp", "math"),
                arrayListOf("aws", "java"),
            )
        )
    )
}


class LeetCode146Solution(val capacity: Int) {

    data class Node(
        var key: Int,
        var value: Int,
        var prevNode: Node? = null,
        var nextNode: Node? = null
    )

    private val map = HashMap<Int, Node>()

    private var head: Node? = null
    private var tail: Node? = null

    fun get(key: Int): Int {
        return if (map.containsKey(key)) {
            val current = map[key]!!

            bringToFront(current)

            current.value
        } else {
            -1
        }
    }

    fun put(key: Int, value: Int) {

        if (map.containsKey(key)) {
            val current = map[key]!!
            current.value = value

            bringToFront(current)
        } else {

            val newNode = Node(key, value)

            map[key] = newNode

            if (head == null) {
                head = newNode
                tail = newNode
            } else {
                tail?.nextNode = newNode
                newNode.prevNode = tail
                tail = newNode
            }

            if (map.size > capacity) {
                val next = head?.nextNode
                map.remove(head?.key)
                head?.nextNode = null
                next?.prevNode = null
                head = next
            }
        }
    }

    fun bringToFront(current: Node) {

        if (current == tail) return

        val prev = current.prevNode
        val next = current.nextNode

        prev?.nextNode = next
        next?.prevNode = prev

        if (head == current) {
            head = current.nextNode
        }

        current.prevNode = tail
        tail?.nextNode = current
        tail = current

        current.nextNode = null

    }
}