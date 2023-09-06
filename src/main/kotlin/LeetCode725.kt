fun main() {

    val head = LeetCode725Solution.ListNode(1)
    head.next = LeetCode725Solution.ListNode(2)
    head.next?.next = LeetCode725Solution.ListNode(3)
    head.next?.next?.next = LeetCode725Solution.ListNode(4)

    val result = LeetCode725Solution().splitListToParts(head, 3)
    println(result)
}


class LeetCode725Solution {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun splitListToParts(head: ListNode?, k: Int): Array<ListNode?> {

        val result = Array<ListNode?>(k) { null }

        val n = getSize(head)

        val minNodesInEachPartition = n / k
        var extraNodeInAllPartitions = n % k

        var index = 0
        var current = head
        var prev: ListNode? = null

        while (index < k) {

            if (current != null) {
                result[index] = current
            }

            repeat(minNodesInEachPartition) {
                prev = current
                current = current?.next
            }

            if (extraNodeInAllPartitions > 0) {
                prev = current
                current = current?.next
                extraNodeInAllPartitions--
            }

            if (prev != null) {
                prev?.next = null
            }

            index++
        }

        return result

    }

    private fun getSize(head: ListNode?): Int {

        if (head == null) return 0

        var current = head
        var count = 0

        while (current != null) {
            count++
            current = current.next
        }

        return count
    }
}