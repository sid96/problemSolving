import java.util.Arrays.copyOf

fun main() {
    val l1 = LeetCode445Solution.ListNode(0)
//    l1.next = LeetCode445Solution.ListNode(4)
//    l1.next?.next = LeetCode445Solution.ListNode(3)
//    l1.next?.next?.next = LeetCode445Solution.ListNode(3)

    val l2 = LeetCode445Solution.ListNode(0)
//    l2.next = LeetCode445Solution.ListNode(6)
//    l2.next?.next = LeetCode445Solution.ListNode(4)

    println(
        LeetCode445Solution().addTwoNumbers(l1, l2)
    )
}


class LeetCode445Solution {

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {

        val reversedL1 = reverseLinkedList(l1)
        val reversedL2 = reverseLinkedList(l2)

        var c1 = reversedL1
        var c2 = reversedL2
        var carry = 0

        var result: ListNode? = null
        var current: ListNode? = null

        while (c1 != null || c2 != null || carry > 0) {
            var digitSum = (c1?.`val` ?: 0) + (c2?.`val` ?: 0) + carry

            carry = digitSum / 10
            digitSum %= 10

            if (result == null) {
                result = ListNode(digitSum)
                current = result
            } else {
                val nextNode = ListNode(digitSum)
                current?.next = nextNode
                current = nextNode
            }

            c1 = c1?.next
            c2 = c2?.next
        }

        result = reverseLinkedList(result)

        return result
    }

    fun reverseLinkedList(head: ListNode?): ListNode? {

        var parent: ListNode? = null
        var next: ListNode? = null
        var current = head

        while (current != null) {

            next = current.next
            current.next = parent
            parent = current
            current = next

        }

        return parent

    }
}