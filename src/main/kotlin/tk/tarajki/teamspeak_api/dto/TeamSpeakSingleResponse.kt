package tk.tarajki.teamspeak_api.dto

import tk.tarajki.teamspeak_api.util.Util

class TeamSpeakSingleResponse(
        string: String
) : TeamSpeakResponse() {

    val data = string.splitToSequence(" ").map {
        val key = it.takeWhile { char ->
            char != '='
        }
        Pair(key, Util.decode(it.drop(key.length + 1)))
    }.toMap()


}