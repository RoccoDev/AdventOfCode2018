package pw.roccodev.aoc.mmxviii

import pw.roccodev.aoc.mmxviii.extras.numberToNumeralString
import java.time.LocalDate

fun main(args: Array<String>) {

    // Get the day in roman numerals

    val day = LocalDate.now().dayOfMonth
    val romanDay = numberToNumeralString(day)

    val todayClass = Class.forName("pw.roccodev.aoc.mmxviii.days.$romanDay.Day$day")

    // Instantiate the class and run the task

    val inst = todayClass.getConstructor().newInstance()
    val now = System.currentTimeMillis()

    val part = 1 // Change to run part 2

    println("Starting...")
    val result = todayClass.methods.find { it.name == "part$part" }?.invoke(inst)
    println("Result: $result")
    println("Done in ${System.currentTimeMillis() - now}ms")

}