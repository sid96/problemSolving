import java.util.LinkedList

fun main() {

    println(
        LeetCode2332Solution().latestTimeCatchTheBus(
            intArrayOf(6,8, 18, 17),
            intArrayOf(6,8,17),
            2
        )
    )
}


class LeetCode2332Solution() {
    fun latestTimeCatchTheBus(buses: IntArray, passengers: IntArray, capacity: Int): Int {
        var ans = 0

        buses.sort()
        passengers.sort()

        val busToPassengersMap = Array<ArrayList<Int>>(buses.size) { ArrayList<Int>() }

        constructBusPassengerMap(passengers, buses, capacity, busToPassengersMap)

        val passengerTimeList = LinkedList<Int>()

        busToPassengersMap.forEach {
            passengerTimeList.addAll(it)
        }

        if(busToPassengersMap.last().size < capacity && (busToPassengersMap.last().isEmpty() || busToPassengersMap.last().last() < buses.last())) {
            ans = buses.last()
            return ans
        }

        for(index in passengerTimeList.size - 2 downTo 0) {
            if(passengerTimeList[index] + 1 != passengerTimeList[index + 1]) {
                ans = passengerTimeList[index + 1] - 1
                break
            }
        }

        if(ans == 0) {

            ans = if(passengerTimeList.isNotEmpty()) {
                passengerTimeList[0] - 1
            } else {
                buses.last()
            }
        }

        return ans
    }

    private fun constructBusPassengerMap(
        passengers: IntArray,
        buses: IntArray,
        capacity: Int,
        busToPassengersMap: Array<ArrayList<Int>>
    ) {
        var busIndex = 0
        var passengerIndex = 0

        while (passengerIndex < passengers.size && busIndex < buses.size) {

            if (passengers[passengerIndex] > buses[busIndex]) {
                busIndex++
            } else {

                val currentPassengerCount = busToPassengersMap[busIndex].size

                if (currentPassengerCount < capacity) {
                    busToPassengersMap[busIndex].add(passengers[passengerIndex])
                    passengerIndex++
                } else {
                    busIndex++
                }
            }
        }
    }
}