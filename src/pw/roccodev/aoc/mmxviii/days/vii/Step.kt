package pw.roccodev.aoc.mmxviii.days.vii

class Step(val id: String, var finished: Boolean = false, val requirements: MutableList<Step> = mutableListOf()) {

    fun finish(string: StringBuilder) {
        if(finished) return
        println("Need to finish $id")
        requirements.sortWith(compareBy {it.id})
        requirements.forEach {
            println("Req: ${it.id}")
            it.finish(string)
        }
        println("Finished $id")
        string.append(id)
        finished = true
    }

}