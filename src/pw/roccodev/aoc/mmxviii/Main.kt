package pw.roccodev.aoc.mmxviii

import pw.roccodev.aoc.mmxviii.extras.numberToNumeralString
import java.time.LocalDate

fun main(args: Array<String>) {

    // Get the day in roman numerals

    val day = LocalDate.now().dayOfMonth
    val romanDay = numberToNumeralString(day)

    val todayClass: Class<DailyChallenge> = Class.forName("pw.roccodev.aoc.mmxviii.days.$romanDay.Day$day")
            as Class<DailyChallenge>

    // Instantiate the class and run the task

    val inst = todayClass.getConstructor().newInstance()

    println("Starting...")
    run(1, todayClass, inst)
    run(2, todayClass, inst)
}

private fun run(part: Int, cls: Class<DailyChallenge>, inst: Any) {
    val now = System.currentTimeMillis()
    val result = cls.methods.find { it.name == "part$part" }?.invoke(inst)
    println("Result for Part $part: $result")
    println("Part $part done in ${System.currentTimeMillis() - now}ms")
}
