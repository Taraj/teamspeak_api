package tk.tarajki.teamspeak_api.util

class Util{
    companion object {
        private val mapOfCharsToEscape = mapOf(
                Pair("\\\\", '\\'),
                Pair("\\/", '/'),
                Pair("\\s", ' '),
                Pair("\\p", '|'),
                Pair("\\b", '\b'),
                Pair("\\n", '\n'),
                Pair("\\r", '\r'),
                Pair("\\t", '\t')
        )

         fun decode(string: String): String {
            var tmp = string
            mapOfCharsToEscape.forEach { (key, value) ->
                tmp = tmp.replace(key, value.toString())
            }
            return tmp
        }
    }
}