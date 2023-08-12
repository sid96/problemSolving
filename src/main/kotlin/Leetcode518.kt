fun main() {
    println(
        Leetcode518Solution().change(
            amount = 5,
            coins = intArrayOf(1, 2, 5)
        )
    )
}

//This problem is of type take it or not take it of Dynamic Programming
class Leetcode518Solution {

    val dp = HashMap<Pair<Int, Int>, Int>()
    fun change(amount: Int, coins: IntArray): Int {

        return solve(amount, 0, coins)

    }

    private fun solve(amount: Int, currentIndex: Int, coins: IntArray): Int {

        //Base condition
        if(amount == 0) {
            return 1
        }

        if(dp.containsKey(Pair(amount, currentIndex))) {
            return dp[Pair(amount, currentIndex)]!!
        }

        var ans = 0

        //try each coin if it's value is <= current amount left
        for (i in currentIndex until coins.size) {
            if(coins[i] <= amount) {
                ans += solve(amount - coins[i], i, coins)
            }
        }

        dp[Pair(amount, currentIndex)] = ans
        return dp[Pair(amount, currentIndex)]!!
    }
}