import kotlin.math.abs

fun main() {
    print(Leetcode735Solution().asteroidCollision(
        intArrayOf(
            -10,9,-8,-10
        )
    ))
}

class Leetcode735Solution {

    fun asteroidCollision(asteroids: IntArray): IntArray {

        var i = asteroids.size - 1

        while(i >= 0) {

            if(asteroids[i] < 0) {
                i--
            } else {

                var j = i +1
                while(j < asteroids.size && asteroids[j] <= 0 && abs(asteroids[i])  > abs(asteroids[j])) {
                    asteroids[j] = 0
                    j++
                }

                if(j < asteroids.size) {
                    if(asteroids[j] <= 0 && abs(asteroids[i])  < abs(asteroids[j])) {
                        asteroids[i] = 0
                    } else if(asteroids[j] <= 0){
                        asteroids[j] = 0
                        asteroids[i] = 0
                    }
                }
                i--
            }
        }

        return asteroids.filter { it != 0 }.toIntArray()

    }
}