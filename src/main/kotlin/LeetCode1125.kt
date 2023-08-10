import java.util.Arrays.copyOf
import java.util.LinkedList

fun main() {

    println(
        LeetCode1125Solution().smallestSufficientTeam(
            arrayOf("algorithms", "math", "java", "reactjs", "csharp", "aws"),
            arrayListOf(
                arrayListOf("algorithms", "math", "java"),
                arrayListOf("algorithms", "math", "reactjs"),
                arrayListOf("java", "csharp", "aws"),
                arrayListOf("reactjs", "csharp"),
                arrayListOf("csharp", "math"),
                arrayListOf("aws", "java"),
            )
        )
    )
}


class LeetCode1125Solution {

    var result = IntArray(61)
    val requiredSkillMap = HashMap<String, Int>()
    val peopleSkillsMap = HashMap<Int, Int>()

    fun smallestSufficientTeam(req_skills: Array<String>, people: List<List<String>>): IntArray {

        req_skills.forEachIndexed { index, item ->
            requiredSkillMap[item] = req_skills.size - 1 - index
        }

        people.forEachIndexed { index, skillList ->
            peopleSkillsMap[index] = getEncodedSkillSet(skillList.toTypedArray())
        }

        val encodedSkills = getEncodedSkillSet(req_skills)

        solve(0, 0, arrayListOf(), encodedSkills, req_skills, people)

        return result
    }

    fun solve(
        currentIndex: Int,
        currentSkillSet: Int,
        consideredPeopleIndex: ArrayList<Int>,
        encodedSkills: Int,
        req_skills: Array<String>,
        people: List<List<String>>
    ): IntArray {

        if ((currentSkillSet xor encodedSkills) == 0) {
            return consideredPeopleIndex.toIntArray()
        }

        if (currentIndex >= people.size || consideredPeopleIndex.size >= result.size) {
            return IntArray(61)
        }

        val leftSubtreeResult = solve(
            currentIndex + 1,
            currentSkillSet or peopleSkillsMap[currentIndex]!!,
            ArrayList(consideredPeopleIndex).apply {
                add(currentIndex)
            },
            encodedSkills,
            req_skills,
            people
        )

        val rightSubtreeResult = solve(
            currentIndex + 1,
            currentSkillSet,
            ArrayList(consideredPeopleIndex),
            encodedSkills,
            req_skills,
            people
        )

        return if (leftSubtreeResult.size <= rightSubtreeResult.size) {

            if (leftSubtreeResult.size < result.size) {
                result = leftSubtreeResult
            }

            leftSubtreeResult
        } else {

            if (rightSubtreeResult.size < result.size) {
                result = rightSubtreeResult
            }

            rightSubtreeResult
        }
    }

    private fun getEncodedSkillSet(reqSkills: Array<String>): Int {
        var encodedValue = 0

        reqSkills.forEach {
            encodedValue = encodedValue or (1 shl requiredSkillMap[it]!!)
        }

        return encodedValue
    }
}