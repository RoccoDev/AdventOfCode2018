package pw.roccodev.aoc.mmxviii.extras

fun numberToNumeralString(number: Int): String {

    val romanNumerals = listOf(
            1000 to "M",
            900 to "CM",
            500 to "D",
            400 to "CD",
            100 to "C",
            90 to "XC",
            50 to "L",
            40 to "XL",
            10 to "X",
            9 to "IX",
            5 to "V",
            4 to "IV",
            1 to "I"
    ).toMap()


    val builder = StringBuilder()

    var elaboration = number

    for((factor, numeral) in romanNumerals) {
        while(elaboration / factor > 0) {
            elaboration -= factor
            builder.append(numeral)
        }
    }

    return builder.toString()

}