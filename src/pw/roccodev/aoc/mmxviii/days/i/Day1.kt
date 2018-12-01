package pw.roccodev.aoc.mmxviii.days.i

import pw.roccodev.aoc.mmxviii.DailyChallenge
import pw.roccodev.aoc.mmxviii.extras.getPuzzleInput

class Day1 : DailyChallenge {

    override fun part2(): Any {

        val cachedFrequencies = arrayListOf(0)
        val lines = getPuzzleInput()
        var count = 0


        while(true) {
            for (line in lines) {
                if (line.startsWith("-")) {
                    count -= Math.abs(line.toInt())
                } else {
                    count += line.replace("+", "").toInt()
                }
                if(cachedFrequencies.contains(count)) {
                    return count
                }
                cachedFrequencies.add(count)
            }

            println("No duplicates found, retrying. Count: $count")

        }

    }

    override fun part1() : Any {

        val lines = getPuzzleInput()
        var count = 0

        lines.forEach {
            if(it.startsWith("-")) {
                count -= Math.abs(it.toInt())
            }
            else {
                count += it.replace("+", "").toInt()
            }
        }

        return count

    }

}