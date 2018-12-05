package pw.roccodev.aoc.mmxviii.days.v

import pw.roccodev.aoc.mmxviii.DailyChallenge
import pw.roccodev.aoc.mmxviii.extras.getPuzzleInput
import java.util.*

class Day5 : DailyChallenge {
    override fun part1(): Any {

        val text = getPuzzleInput()[0]
        val chars = text.toCharArray()
        val stack = Stack<Char>()

        chars.forEach {
            if(!stack.empty() && Math.abs(stack.peek() - it) == 'a' - 'A') stack.pop()
            else stack.push(it)
        }

        return stack.size
    }

    override fun part2(): Any {
        val text = getPuzzleInput()[0]
        val chars = text.toCharArray()

        var minResult = Int.MAX_VALUE

        var a = 'a'
        while(a <= 'z') {
            val stack = Stack<Char>()
            val charsToUse = chars.filter { it != a && it != a.toUpperCase() }
            charsToUse.forEach {
                if (!stack.empty() && Math.abs(stack.peek() - it) == 'a' - 'A') stack.pop()
                else stack.push(it)
            }
            if(stack.size < minResult) minResult = stack.size
            a++
        }

        return minResult
    }


}