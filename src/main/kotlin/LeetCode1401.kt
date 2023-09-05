import kotlin.math.sqrt

fun main() {
    val result = LeetCode1401Solution().checkOverlap(
        1, 0, 0, 1, -1, 3, 1
    )

    print(result)
}

class LeetCode1401Solution {

    fun checkOverlap(r: Int, xCenter: Int, yCenter: Int, x1: Int, y1: Int, x2: Int, y2: Int): Boolean {

        //equation of circle => x^2 + y^2 = r^2
        //check for the intersection points

        //check for intersection on x = x1
        val ipy1Sq = (r - x1 + xCenter).toDouble() * (r + x1 - xCenter).toDouble()

        if (ipy1Sq >= 0) {
            val ipy1 = sqrt(ipy1Sq)

            if (ipy1 + yCenter >= y1 && ipy1 + yCenter <= y2) {
                return true
            } else if (-ipy1 + yCenter >= y1 && -ipy1 + yCenter <= y2) {
                return true
            }
        }


        //check for intersection on x = x2
        val ipy2Sq = (r - x2 + xCenter).toDouble() * (r + x2 - xCenter).toDouble()

        if (ipy2Sq >= 0) {
            val ipy2 = sqrt(ipy2Sq)
            if (ipy2 + yCenter >= y1 && ipy2 + yCenter <= y2) {
                return true
            } else if (-ipy2 + yCenter >= y1 && -ipy2 + yCenter <= y2) {
                return true
            }
        }


        //check for intersection on y = y1
        val ipx1Sq = (r - y1 + yCenter).toDouble() * (r + y1 - yCenter).toDouble()

        if (ipx1Sq >= 0) {
            val ipx1 = sqrt(ipx1Sq)
            if (ipx1 + xCenter >= x1 && ipx1 + xCenter <= x2) {
                return true
            } else if (-ipx1 + xCenter >= x1 && -ipx1 + xCenter <= x2) {
                return true
            }
        }


        //check for intersection on y = y2
        val ipx2Sq = (r - y2 + yCenter).toDouble() * (r + y2 - yCenter).toDouble()

        if (ipx2Sq >= 0) {
            val ipx2 = sqrt(ipx2Sq)

            if (ipx2 + xCenter >= x1 && ipx2 + xCenter <= x2) {
                return true
            } else if (-ipx2 + xCenter >= x1 && -ipx2 + xCenter <= x2) {
                return true
            }
        }


        //if circle lies completely inside the rectangle
        if (xCenter in x1..x2 && yCenter >= y1 && yCenter <= y2) {
            return true
        }

        val rectCenterX = x1 + (x2.toDouble() - x1.toDouble()) / 2
        val rectCenterY = y1 + (y2.toDouble() - y1.toDouble()) / 2

        //if rectangle lies completely inside the circle
        val dist =
            sqrt((xCenter - rectCenterX) * (xCenter - rectCenterX) + (yCenter - rectCenterY) * (yCenter - rectCenterY))

        return dist < r

    }


}