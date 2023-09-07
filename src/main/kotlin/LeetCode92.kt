fun main() {
    val head = LeetCode92Solution.ListNode(1)
    head.next = LeetCode92Solution.ListNode(2)
    head.next?.next = LeetCode92Solution.ListNode(3)
    head.next?.next?.next = LeetCode92Solution.ListNode(4)
    head.next?.next?.next?.next = LeetCode92Solution.ListNode(5)

    val result = LeetCode92Solution().reverseBetween(
        head,
        2,
        4
    )

    print(result)
}


class LeetCode92Solution {

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun reverseBetween(head: ListNode?, left: Int, right: Int): ListNode? {

        var index = 0

        val indexLeft = left - 1
        val indexRight = right - 1

        var current = head

        var nodeLeft: ListNode? = null
        var nodeLeftPrev: ListNode? = null

        var nodeRight: ListNode? = null
        var nodeRightNext: ListNode? = null

        while (current != null) {

            if (index == indexLeft) {
                nodeLeft = current
            }

            if (nodeLeft == null) {
                nodeLeftPrev = current
            }

            if (index == indexRight) {
                nodeRight = current
                nodeRightNext = nodeRight.next
                break
            }

            index++
            current = current.next
        }


        current = nodeLeft
        var prev: ListNode? = null

        while (current != nodeRightNext) {
            val temp = current?.next
            current?.next = prev
            prev = current
            current = temp
        }

        if (nodeLeftPrev != null) {
            nodeLeftPrev.next = nodeRight
        }

        nodeLeft?.next = nodeRightNext

        return if (nodeLeftPrev == null) {
            nodeRight
        } else {
            head
        }
    }
}