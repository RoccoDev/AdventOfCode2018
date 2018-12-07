package pw.roccodev.aoc.mmxviii.days.vii

import pw.roccodev.aoc.mmxviii.DailyChallenge
import pw.roccodev.aoc.mmxviii.extras.getPuzzleInput

class Day7 : DailyChallenge {


    inline fun <K, V> MutableMap<K, V>.drop(
            predicate: (K, V) -> Boolean = { _, _ -> true }
    ): Map.Entry<K, V>? {
        val iterator = iterator()
        while (iterator.hasNext()) {
            val (key, value) = iterator.next()
            if (predicate(key, value)) {
                iterator.remove()
                return object : Map.Entry<K, V> {
                    override val key = key
                    override val value = value
                }
            }
        }
        return null
    }

    inline fun <T> MutableIterable<T>.drop(
            predicate: (T) -> Boolean = { true }
    ): T? {
        val iterator = iterator()
        while (iterator.hasNext()) {
            val next = iterator.next()
            if (predicate(next)) {
                iterator.remove()
                return next
            }
        }
        return null
    }


    override fun part1(): Any {

        val pattern = """Step (.+) must be finished before step (.+) can begin.""".toRegex()
        val lines = getPuzzleInput()
        var steps = mutableMapOf<String, Step>()

        lines.forEach {
            val (requirement, id) = pattern.matchEntire(it)!!.destructured
            val step = steps.getOrElse(id) {Step(id)}
            step.requirements.add(steps.getOrPut(requirement) {Step(requirement)})

            steps[id] = step
        }

        steps = steps.toSortedMap(compareBy { it })

        val string = StringBuilder()

        while(true) {
            val (k) = steps.drop {k, v -> v.requirements.isEmpty()} ?: break
            steps.values.forEach { it.requirements.removeIf {it.id == k}}
            string.append(k)
        }


        return string.toString()
    }



    override fun part2(): Any {
        val pattern = """Step (.+) must be finished before step (.+) can begin.""".toRegex()
        val lines = getPuzzleInput()
        val steps = mutableMapOf<String, Step>()
        val totalReqs = mutableListOf<Step>()

        lines.forEach {
            val (requirement, id) = pattern.matchEntire(it)!!.destructured
            val step = steps.getOrElse(id) {Step(id)}
            step.requirements.add(steps.getOrPut(requirement) {Step(requirement)})
            if(!totalReqs.contains(steps[requirement]!!))
            totalReqs.add(steps[requirement]!!)

            steps[id] = step
        }

        val count = 5

        val working = sortedSetOf(
                comparator = compareBy(Pair<Int, String>::first).thenBy(Pair<Int, String>::second)
        )
            var time = 0
            do {
                working.drop()?.let { (t, k) ->
                    time = t
                    steps.remove(k)
                    steps.values.forEach { it.requirements.removeIf{it.id == k} }
                }
                while (working.size < count) {
                    val (k) = steps.drop { _, value -> value.requirements.isEmpty() } ?: break
                    working.add(time + 61 + k[0].toInt() - 'A'.toInt() to k)
                }
            } while (working.isNotEmpty())

            return time
        }

    }