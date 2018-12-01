package pw.roccodev.aoc.mmxviii.extras

import java.io.File
import java.nio.charset.Charset
import java.time.LocalDate

fun getPuzzleInput() : List<String> {
    return File("res/day${LocalDate.now().dayOfMonth}").readLines(Charset.defaultCharset())
}