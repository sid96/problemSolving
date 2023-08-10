import java.util.*
import java.util.Arrays.copyOf
import javax.swing.tree.TreeNode
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

fun main() {
    println(
        LeetCode1601Solution().maximumRequests(
            7,
            arrayOf(
                intArrayOf(0, 1),
                intArrayOf(1, 2),
                intArrayOf(2, 3),
                intArrayOf(3, 0),
                intArrayOf(4, 2),
                intArrayOf(3, 5),
                intArrayOf(5, 6),
                intArrayOf(6, 4)
            )
        )
    )
}


class LeetCode863Solution {

    class TreeNode(var `val`: Int = 0) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun distanceK(root: TreeNode?, target: TreeNode?, k: Int): List<Int> {

        val parentOf = HashMap<Int, TreeNode>()

        solve(root!!, target!!.`val`, parentOf)

        val result = arrayListOf<Int>()

        findParentAtDistanceK(parentOf, target, k, result)

        result.addAll(levelOrderTraverse(target, k))


        return result.toList()

    }

    fun findParentAtDistanceK(parentOf: HashMap<Int, TreeNode>, target: TreeNode, k: Int, result: ArrayList<Int>) {
        var current = target
        var level = 0

        while(level < k && parentOf.containsKey(current.`val`)) {
            current = parentOf[current.`val`]!!
            level++
        }

        if(level == k) {
            if(k != 0) {
                result.add(current.`val`)
            }
        } else {
            if(parentOf.containsKey(current.left!!.`val`) && current.right != null) {
                result.addAll(levelOrderTraverse(current.right!!, k-level-1))
            } else if(parentOf.containsKey(current.right!!.`val`) && current.left != null) {
                result.addAll(levelOrderTraverse(current.left!!, k-level-1))
            }
        }
    }

    fun solve(current: TreeNode, target: Int, parentOf: HashMap<Int, TreeNode>): Boolean {

        if(current.`val` == target) return true

        if(current.left != null) {
            parentOf[current.left!!.`val`] = current
            if(solve(current.left!!, target, parentOf)) {
                return true
            } else {
                parentOf.remove(current.left!!.`val`)
            }
        }

        if(current.right != null) {
            parentOf[current.right!!.`val`] = current

            if(solve(current.right!!, target, parentOf)) {
                return true
            } else {
                parentOf.remove(current.right!!.`val`)
            }
        }

        return false

    }

    fun levelOrderTraverse(target: TreeNode, k: Int): ArrayList<Int> {
        val nodesAtDistanceK = arrayListOf<Int>()
        val queue = LinkedList<TreeNode>()
        queue.addLast(target)
        var currentLevel = 0

        while(queue.isEmpty().not()){
            var size = queue.size

            while(size > 0) {

                val current = queue.removeFirst()

                if(currentLevel == k) {
                    nodesAtDistanceK.add(current.`val`)
                } else {
                    if(current.left != null) queue.addLast(current.left)
                    if(current.right != null) queue.addLast(current.right)
                }

                size--
            }

            currentLevel++
        }

        return nodesAtDistanceK
    }
}