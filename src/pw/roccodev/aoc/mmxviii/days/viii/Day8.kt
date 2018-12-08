package pw.roccodev.aoc.mmxviii.days.viii

import pw.roccodev.aoc.mmxviii.DailyChallenge
import pw.roccodev.aoc.mmxviii.extras.getPuzzleInput

class Day8 : DailyChallenge {

    override fun part1(): Any {

        val nums = getPuzzleInput()[0].split(" ").mapNotNull { it.toIntOrNull() }.toIntArray()

        val iterator = nums.iterator()

        return read(iterator) { ints1, ints2 -> ints1.sum() + ints2.sum() }
    }

    private fun read(iterator: IntIterator, map: (IntArray, IntArray) -> Int) : Int {
        // Read header
        val childrenCount = iterator.nextInt()
        val metaCount = iterator.nextInt()

        // Read children and meta
        val children = IntArray(childrenCount) { read(iterator, map) }
        val metadata = IntArray(metaCount) { iterator.nextInt() }

        return map(children, metadata)
    }

    override fun part2(): Any {
        val nums = getPuzzleInput()[0].split(" ").mapNotNull { it.toIntOrNull() }.toIntArray()

        val iterator = nums.iterator()

        return read(iterator) { ints1, ints2 ->
            if(ints1.isEmpty()) ints2.sum()
            else ints2.sumBy { ints1.getOrElse(it - 1) {0} }
        }
    }
}