package pw.roccodev.aoc.mmxviii.days.ii

import pw.roccodev.aoc.mmxviii.DailyChallenge
import pw.roccodev.aoc.mmxviii.extras.getPuzzleInput

class Day2 : DailyChallenge {


    override fun part2(): Any {
        val lines = getPuzzleInput()
        lines.forEach {
            val line = it
            lines.forEach {
                if(getDifferences(it, line) == 1) return getStringWithoutDifferences(it, line)
            }
        }
        return 0
    }

    override fun part1(): Any {
        val lines = getPuzzleInput()
        var twos = 0
        var threes = 0
        lines.forEach {
            val knownChars = hashMapOf<Char, Int>()



            it.toCharArray().forEach {
                if(!knownChars.containsKey(it)) knownChars[it] = 1
                else {
                    knownChars[it] = knownChars[it]?.plus(1)!!
                }
            }

            var check2 = true
            var check3 = true

            knownChars.forEach {
                if(check2) {
                    if(knownChars.filterValues { it == 2 }.count() != 0) {
                        twos++
                        check2 = false
                    }
                }
                if(check3) {
                    if(knownChars.filterValues { it == 3 }.count() != 0) {
                        threes++
                        check3 = false
                    }
                }
            }

        }

        return twos * threes

    }


    private fun getDifferences(string1: String, string2: String) : Int {
        val chars1 = string1.toCharArray()
        val chars2 = string2.toCharArray()

        var differences = 0

        for((index, value) in chars1.withIndex()) {
            if(value != chars2[index]) differences++
        }

        return differences
    }

    private fun getStringWithoutDifferences(string1: String, string2: String) : String {
        val chars1 = string1.toCharArray()
        val chars2 = string2.toCharArray()

        val builder = StringBuilder()

        for((index, value) in chars1.withIndex()) {
            if(value == chars2[index]) builder.append(value)
        }

        return builder.toString()
    }

}