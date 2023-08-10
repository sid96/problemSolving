fun main() {

    println(
        LeetCode920Solution().numMusicPlaylists(
            16, 16, 1
        )
    )
}


class LeetCode920Solution() {

    fun numMusicPlaylists(n: Int, goal: Int, k: Int): Int {

       return solve(0, n, goal, k, HashMap()).toInt()
    }

    private fun solve(playlistLength: Int, totalSongs: Int, goal: Int, k: Int, lastIndexOf: HashMap<Int, Int>): Long {

        if(playlistLength == goal){
            return if(lastIndexOf.size == totalSongs) 1 else 0
        }

        var result = 0L

        for(i in 1..totalSongs) {

            if(lastIndexOf.containsKey(i)) {
                if((playlistLength + 1 - lastIndexOf[i]!! - 1) < k) {
                    continue
                } else if(goal - playlistLength + 1 < totalSongs - lastIndexOf.size) {
                    return result
                }
            }

            result += solve(playlistLength + 1, totalSongs, goal, k, HashMap(lastIndexOf).apply {
                put(i, playlistLength + 1)
            })

            result %= 1000000007
        }

        return result
    }
}