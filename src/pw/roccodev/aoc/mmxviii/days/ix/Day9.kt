package pw.roccodev.aoc.mmxviii.days.ix

import pw.roccodev.aoc.mmxviii.DailyChallenge
import pw.roccodev.aoc.mmxviii.extras.getPuzzleInput
import java.util.*

class Day9 : DailyChallenge {

    override fun part1(): Any {
        val input = getPuzzleInput()[0]
        val deque = ArrayDeque<Int>()

        deque.addFirst(0)

        val pattern = """(\d+) players; last marble is worth (\d+) points""".toRegex()
        val (sp, sm) = pattern.matchEntire(input)!!.destructured
        val players = sp.toInt()
        val max = sm.toInt()

        val scores = IntArray(players)

        for (i in 1..max) {
            if (i % 23 == 0) {
                rotateBoard(deque, -7)
                scores[i % players] += deque.pop() + i
            } else {
                rotateBoard(deque, 2)
                deque.addLast(i)
            }
        }



        return scores.max()!!
    }

    private fun rotateBoard(board: ArrayDeque<Int>, offset: Int) {
        if (offset >= 0) {
            for (i in 0 until offset) {
                board.addFirst(board.removeLast())
            }
        } else {
            for (i in 0 until -offset - 1) {
                board.addLast(board.remove())
            }
        }
    }

    override fun part2(): Any {
        val input = getPuzzleInput()[0]
        val deque = ArrayDeque<Int>()

        deque.addFirst(0)

        val pattern = """(\d+) players; last marble is worth (\d+) points""".toRegex()
        val (sp, sm) = pattern.matchEntire(input)!!.destructured
        val players = sp.toInt()
        val max = sm.toInt() * 100

        val scores = LongArray(players) // The result will be so big it will overflow if we choose Int

        for (i in 1..max) {
            if (i % 23 == 0) {
                rotateBoard(deque, -7)
                scores[i % players] += deque.pop().toLong() + i
            } else {
                rotateBoard(deque, 2)
                deque.addLast(i)
            }
        }



        return scores.max()!!
    }
}