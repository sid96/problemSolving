import java.util.LinkedList

fun main() {

    val root = LeetCode1110Solution.TreeNode(1)

    root.left = LeetCode1110Solution.TreeNode(5)
    root.right = LeetCode1110Solution.TreeNode(2)

    root.right?.right = LeetCode1110Solution.TreeNode(3)

    root.right?.right?.left = LeetCode1110Solution.TreeNode(4)


    val result = LeetCode1110Solution().delNodes(
        root,
        intArrayOf(1, 5)
    )

    print(result)

}


class LeetCode1110Solution() {

    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun delNodes(root: TreeNode?, to_delete: IntArray): List<TreeNode?> {

        val roots = LinkedList<TreeNode>()
        roots.addLast(root)

        to_delete.forEach { itemToDelete ->

            val iterations = roots.size
            for (i in 0 until iterations) {

                val newRoots = search(roots[i], null, itemToDelete)

                if (newRoots.isEmpty().not()) {
                    roots.addAll(newRoots)
                    break
                }
            }
        }

        val result = arrayListOf<TreeNode>()


        roots.forEach {
            if (to_delete.contains(it.`val`).not()) {
                result.add(it)
            }
        }

        return result
    }

    private fun search(root: TreeNode?, parent: TreeNode?, itemToDelete: Int): List<TreeNode> {

        if (root == null) return listOf()

        if (root.`val` == itemToDelete) {

            val newRoots = arrayListOf<TreeNode>()

            if (root.left != null) {
                root.left?.let {
                    newRoots.add(it)
                }
            }

            if (root.right != null) {
                root.right?.let {
                    newRoots.add(it)
                }
            }

            if (parent != null && parent.left?.`val` == itemToDelete) {
                parent.left = null
            } else if (parent != null && parent.right?.`val` == itemToDelete) {
                parent.right = null
            }

            return newRoots
        }

        val newRootsLeft = search(root.left, root, itemToDelete)

        return if (newRootsLeft.isEmpty()) {
            search(root.right, root, itemToDelete)
        } else {
            newRootsLeft
        }
    }
}