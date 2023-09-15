fun main() {

    println(
        LeetCode332Solution().findItinerary(
            arrayListOf(
                arrayListOf("EZE", "AXA"),
                arrayListOf("TIA", "ANU"),
                arrayListOf("ANU", "JFK"),
                arrayListOf("JFK", "ANU"),
                arrayListOf("ANU", "EZE"),
                arrayListOf("TIA", "ANU"),
                arrayListOf("AXA", "TIA"),
                arrayListOf("TIA", "JFK"),
                arrayListOf("ANU", "TIA"),
                arrayListOf("JFK", "TIA"),
            )
        )
    )
}


class LeetCode332Solution {


    fun findItinerary(tickets: List<List<String>>): List<String> {
        val destinationsSet = HashSet<String>()

        tickets.forEach { ticket ->
            destinationsSet.add(ticket[0])
            destinationsSet.add(ticket[1])
        }

        val destinationsArray = Array<String>(destinationsSet.size) { "" }
        destinationsSet.toArray(destinationsArray)

        destinationsArray.sort()

        val destinationsToIdMap = HashMap<String, Int>()
        val idToDestinationMap = HashMap<Int, String>()

        destinationsArray.forEachIndexed { index, destination ->
            destinationsToIdMap[destination] = index
            idToDestinationMap[index] = destination
        }

        val adjMatrix = Array<IntArray>(destinationsArray.size) { IntArray(destinationsArray.size) { 0 } }

        tickets.forEach { ticket ->
            adjMatrix[destinationsToIdMap[ticket[0]]!!][destinationsToIdMap[ticket[1]]!!]++
        }

        val itinerary = arrayListOf<Int>()

        val source = destinationsToIdMap["JFK"]!!

        itinerary.add(source)

        dfs(source, adjMatrix, itinerary, tickets.size)

        val result = arrayListOf<String>()

        itinerary.forEach { item ->
            result.add(idToDestinationMap[item]!!)
        }

        return result
    }

    private fun dfs(
        currentDestId: Int,
        adjMatrix: Array<IntArray>,
        itinerary: ArrayList<Int>,
        totalTickets: Int
    ): Boolean {

        for (index in 0 until adjMatrix.size) {

            val currentTicket = Pair(currentDestId, index)

            if (adjMatrix[currentTicket.first][currentTicket.second] > 0) {

                itinerary.add(index)
                adjMatrix[currentTicket.first][currentTicket.second]--

                if (dfs(index, adjMatrix, itinerary, totalTickets).not()) {
                    itinerary.removeLast()
                    adjMatrix[currentTicket.first][currentTicket.second]++
                }

            }

        }

        return itinerary.size == totalTickets + 1
    }

}