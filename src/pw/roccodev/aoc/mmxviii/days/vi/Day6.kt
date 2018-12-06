package pw.roccodev.aoc.mmxviii.days.vi

import pw.roccodev.aoc.mmxviii.DailyChallenge
import pw.roccodev.aoc.mmxviii.extras.getPuzzleInput

class Day6 : DailyChallenge {

    override fun part1(): Any {
        val pattern = """(\d+), (\d+)""".toRegex()
        val lines = getPuzzleInput()
        val coords = mutableMapOf<Int, Pair<Int, Int>>() // id, point
        val closests = mutableMapOf<Int, Int>()
        var maxX = 0
        var maxY = 0


        var id = 0
        lines.forEach {
            val (xs, ys) = pattern.matchEntire(it)!!.destructured
            val x = xs.toInt()
            val y = ys.toInt()

            if(x > maxX) maxX = x
            if(y > maxY) maxY = y

            coords[++id] = x to y
        }

        val gridX = maxX + 1
        val gridY = maxY + 1


        val grid =  Array(gridX) {
            Array(gridY) {0}
        }

        // Check every point in the grid
        for(x in 0..maxX) {
            for(y in 0..maxY) {

                // Get closest coordinate
                var closestCoord = 0
                var closestDist = Int.MAX_VALUE
                coords.forEach {
                    // Manhattan distance - Sum of the absolute values of the coord differences

                    val v = it.value

                    val mht = Math.abs(x - v.first) + Math.abs(y - v.second)
                    if(mht < closestDist) {
                        closestDist = mht
                        closestCoord = it.key
                    }
                    else if(closestDist == mht) closestCoord = -1
                }

                grid[x][y] = closestCoord

                closests[closestCoord] = closests.getOrElse(closestCoord) {0} + 1


            }
        }

        for(x in 0..maxX) {
            val remove = grid[x][0]
            closests.remove(remove)

            val remove2 = grid[x][maxY]
            closests.remove(remove2)
        }

        for(y in 0..maxY) {
            val remove = grid[0][y]
            closests.remove(remove)

            val remove2 = grid[maxX][y]
            closests.remove(remove2)
        }

        return closests.values.max()!!
    }

    override fun part2(): Any {
        val pattern = """(\d+), (\d+)""".toRegex()
        val lines = getPuzzleInput()
        val coords = mutableMapOf<Int, Pair<Int, Int>>() // id, point
        var maxX = 0
        var maxY = 0
        var id = 0
        lines.forEach {
            val (xs, ys) = pattern.matchEntire(it)!!.destructured
            val x = xs.toInt()
            val y = ys.toInt()

            if(x > maxX) maxX = x
            if(y > maxY) maxY = y

            coords[++id] = x to y
        }

        var size = 0
        for(x in 0..maxX) {
            for(y in 0..maxY) {
                var temp = 0
                coords.forEach {
                    val v = it.value
                    val mht = Math.abs(x - v.first) + Math.abs(y - v.second)
                    temp += mht
                }
                if(temp < 10000) size++
            }
        }


        return size
    }
}