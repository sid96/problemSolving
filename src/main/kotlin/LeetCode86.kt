fun main() {

    val head = LeetCode86Solution.ListNode(2)
//    head.next = LeetCode86Solution.ListNode(4)
//    head.next?.next = LeetCode86Solution.ListNode(3)
//    head.next?.next?.next = LeetCode86Solution.ListNode(2)
//    head.next?.next?.next?.next = LeetCode86Solution.ListNode(5)
//    head.next?.next?.next?.next?.next = LeetCode86Solution.ListNode(2)
    head.next = LeetCode86Solution.ListNode(3)

    var current = LeetCode86Solution().partition(
        head, 3
    )

    while(current != null) {
        print("${current.`val`} ")
        current = current.next
    }
}

class LeetCode86Solution() {

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun partition(head: ListNode?, x: Int): ListNode? {

        if (head?.next == null) return head

        var beforePivot = findNodeJustBeforePivot(head, x)

        if(beforePivot != null  && beforePivot.next == null) return head

        var result = head

        var current = if (beforePivot == null) head else beforePivot.next
        var previous: ListNode? = null

        while (current != null) {

            if(current.`val` < x) {

                val temp = current.next

                if(beforePivot != null) {
                    current.next = beforePivot.next
                    beforePivot.next = current
                } else {
                    current.next = head
                    result = current
                }

                beforePivot = current
                previous?.next = temp
            }

            previous = current
            current = previous.next
        }

        return result

    }

    fun findNodeJustBeforePivot(head: ListNode?, x: Int): ListNode? {

        var previous: ListNode? = null
        var current = head


        while (current != null) {

            if(current.`val` >= x) {
                break
            }

            previous = current
            current = previous.next
        }

        return previous
    }
}