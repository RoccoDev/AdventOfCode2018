package pw.roccodev.aoc.mmxviii.days.iv

import pw.roccodev.aoc.mmxviii.DailyChallenge
import pw.roccodev.aoc.mmxviii.extras.getPuzzleInput
import java.text.SimpleDateFormat
import java.util.*

class Day4 : DailyChallenge {

    override fun part1(): Any {

        val guards = mutableMapOf<Int, IntArray>()
        var currentGuard = 0
        var minute = 0
        val formatter = SimpleDateFormat("yyyy-MM-dd hh:mm")
        val pattern = """\[(.+)] (.+)""".toRegex()
        val guardPattern = """Guard #(.+) begins shift""".toRegex()

        val lines = ArrayList<String>(getPuzzleInput())
        lines.sortWith(Comparator { s1, s2 ->
            val (dateStr) = pattern.matchEntire(s1)!!.destructured
            val (dateStr2) = pattern.matchEntire(s2)!!.destructured

            formatter.parse(dateStr).compareTo(formatter.parse(dateStr2))
        })
        lines.forEach {

            val (date, other) = pattern.matchEntire(it)!!.destructured

            val dateObj = formatter.parse(date)
            val calendar = Calendar.getInstance()
            calendar.time = dateObj

            when {
                other == "wakes up" -> {
                    val arr = guards.getOrPut(currentGuard) { IntArray(60) }
                    (minute until calendar[Calendar.MINUTE]).forEach {arr[it]++}
                }
                other == "falls asleep" -> {
                    minute = calendar[Calendar.MINUTE]
                }
                other.startsWith("Guard") -> {
                    val (id) = guardPattern.matchEntire(other)!!.destructured
                    currentGuard = id.toInt()
                }
            }




        }

        val maxMinute = guards.maxBy { it.value.sum() }!!
        val maxGuard = maxMinute.value.withIndex().maxBy { it.value }!!.index

        return maxGuard * maxMinute.key
    }

    override fun part2(): Any {
        val guards = mutableMapOf<Int, IntArray>()
        var currentGuard = 0
        var minute = 0
        val formatter = SimpleDateFormat("yyyy-MM-dd hh:mm")
        val pattern = """\[(.+)] (.+)""".toRegex()
        val guardPattern = """Guard #(.+) begins shift""".toRegex()

        val lines = ArrayList<String>(getPuzzleInput())
        lines.sortWith(Comparator { s1, s2 ->
            val (dateStr) = pattern.matchEntire(s1)!!.destructured
            val (dateStr2) = pattern.matchEntire(s2)!!.destructured

            formatter.parse(dateStr).compareTo(formatter.parse(dateStr2))
        })
        lines.forEach {

            val (date, other) = pattern.matchEntire(it)!!.destructured

            val dateObj = formatter.parse(date)
            val calendar = Calendar.getInstance()
            calendar.time = dateObj

            when {
                other == "wakes up" -> {
                    val arr = guards.getOrPut(currentGuard) { IntArray(60) }
                    (minute until calendar[Calendar.MINUTE]).forEach {arr[it]++}
                }
                other == "falls asleep" -> {
                    minute = calendar[Calendar.MINUTE]
                }
                other.startsWith("Guard") -> {
                    val (id) = guardPattern.matchEntire(other)!!.destructured
                    currentGuard = id.toInt()
                }
            }




        }

        // Just the same but for each key

        val maxPair = guards.map { it.key to it.value.withIndex().maxBy { it.value }}.maxBy { it.second!!.value }


        return maxPair?.first!! * maxPair.second!!.index
    }
}