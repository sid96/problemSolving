import java.util.*

fun main() {
    val myStack = MyStackSingleQueue()
    myStack.push(1)
    myStack.push(2)
    println(myStack.top())// return 2

    println(myStack.pop()) // return 2

    println(myStack.empty()) // return False

}

class MyStackSingleQueue {
    private var queue1 = LinkedList<Int>()

    fun push(x: Int) {
        queue1.addLast(x)
    }

    fun pop(): Int {
        reverse()
        val last = queue1.removeFirst()
        reverse()
        return last
    }

    private fun reverse() {
        if(queue1.size == 0) {
            return
        }

        val current = queue1.removeFirst()
        reverse()
        queue1.addLast(current)
    }

    fun top(): Int {
        val last = pop()
        queue1.addLast(last)
        return last
    }

    fun empty(): Boolean {
        return queue1.isEmpty()
    }
}


class MyStack {

    private var queue1 = LinkedList<Int>()
    private var queue2 = LinkedList<Int>()

    fun push(x: Int) {
        queue1.addLast(x)
    }

    fun pop(): Int {

        while (queue1.size != 1) {
            queue2.addLast(queue1.removeFirst())
        }

        val last = queue1.removeFirst()

        val temp = queue1
        queue1 = queue2
        queue2 = temp

        return last
    }

    fun top(): Int {
        val last = pop()
        queue1.addLast(last)
        return last
    }

    fun empty(): Boolean {
        return queue1.isEmpty()
    }

}
