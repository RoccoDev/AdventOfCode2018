package pw.roccodev.aoc.mmxviii

import pw.roccodev.aoc.mmxviii.extras.numberToNumeralString
import java.time.LocalDate

fun main(args: Array<String>) {

    // Get the day in roman numerals

    val day = LocalDate.now().dayOfMonth
    val romanDay = numberToNumeralString(day)

    val todayClass = Class.forName("pw.roccodev.aoc.mmxviii.day.$romanDay.Day$day")

    // Instantiate the class and run the task

    val inst = todayClass.getConstructor().newInstance()
    todayClass.methods.find { it.name == "run" }?.invoke(inst)


}