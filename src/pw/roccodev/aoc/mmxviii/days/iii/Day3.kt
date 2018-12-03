package pw.roccodev.aoc.mmxviii.days.iii

import pw.roccodev.aoc.mmxviii.DailyChallenge
import pw.roccodev.aoc.mmxviii.extras.getPuzzleInput

class Day3 : DailyChallenge {

    override fun part1(): Any {
        val grid = mutableMapOf<Pair<Int, Int>, Int>()

        val lines = getPuzzleInput()

        var overlapping = 0

        val pattern = """#(\d+) @ (\d+),(\d+): (\d+)x(\d+)""".toRegex()

        lines.forEach {
            // Parsing the string
            val match = pattern.matchEntire(it)!!
            val (idMatch, xMatch, yMatch, widthMatch, heightMatch) = match.destructured

            val x = xMatch.toInt()
            val y = yMatch.toInt()

            val width = widthMatch.toInt()
            val height = heightMatch.toInt()

            val areaX = x until x + width
            val areaY = y until y + height

            for(i in areaX) {
                for(j in areaY) {
                    val posToAdd = i to j
                    val current = grid.getOrElse(posToAdd) {0}

                    grid[posToAdd] = current + 1

                    if(current == 1) overlapping++

                }
            }

        }

        return overlapping
    }

    override fun part2(): Any {
        val grid = mutableMapOf<Pair<Int, Int>, String>()
        val ids = mutableListOf<String>()

        val lines = getPuzzleInput()

        val pattern = """#(\d+) @ (\d+),(\d+): (\d+)x(\d+)""".toRegex()

        lines.forEach {
            // Parsing the string
            val match = pattern.matchEntire(it)!!
            val (idMatch, xMatch, yMatch, widthMatch, heightMatch) = match.destructured

            val x = xMatch.toInt()
            val y = yMatch.toInt()

            val width = widthMatch.toInt()
            val height = heightMatch.toInt()

            val areaX = x until x + width
            val areaY = y until y + height

            ids.add(idMatch)

            for(i in areaX) {
                for(j in areaY) {
                    val posToAdd = i to j
                    val current = grid.getOrPut(posToAdd) {idMatch}

                    if(current != idMatch) {
                        ids.remove(current)
                        ids.remove(idMatch)
                    }

                }
            }

        }

        return ids[0]
    }
}