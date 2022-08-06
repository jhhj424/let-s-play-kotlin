package jiho.programmers.kakao

// https://school.programmers.co.kr/learn/courses/30/lessons/92334

class 신고결과받기kt {

    fun main(args: Array<String>) {
        println(
            "solution(idList, report, k) = " + solution(
                arrayOf("con", "ryan"),
                arrayOf("ryan con", "ryan con", "ryan con", "ryan con"),
                3
            )
        )

        println(
            "solution(idList, report, k) = " + solution(
                arrayOf("muzi", "frodo", "apeach", "neo"),
                arrayOf("muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"),
                2
            )
        )
    }


    private fun solution(id_list: Array<String>, report: Array<String>, k: Int): List<Int> {
        val answer = ArrayList<Int>()

        val userMap: MutableMap<String, MutableSet<String>> = HashMap()
        val idListMap: MutableMap<String, MutableSet<String>> = HashMap()
        for (id in id_list) {
            idListMap[id] = HashSet()
            userMap[id] = HashSet()
        }

        // map 에 매핑하기
        for (r in report) {
            val s = r.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val a = s[0] // 신고자
            val b = s[1] // 신고 당한자

            // 신고 한 유저 map
            idListMap[a]!!.add(b)

            // 신고 횟수 map
            userMap[b]!!.add(a)
        }

        // map 중에 value 의 size 가 k 이상인 경우 key 값은 신고 된 사용자
        val users = ArrayList<String>()
        for (key in userMap.keys) {
            if (userMap[key]!!.size >= k) {
                users.add(key)
            }
        }
        for (id in id_list) {
            var count = 0
            for (targetUser in idListMap[id]!!) {
                if (users.contains(targetUser)) {
                    count++
                }
            }
            answer.add(count)
        }
        return answer
    }
}
