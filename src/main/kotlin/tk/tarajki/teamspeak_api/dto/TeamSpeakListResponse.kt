package tk.tarajki.teamspeak_api.dto

class TeamSpeakListResponse(
        string: String
) : TeamSpeakResponse() {
    val data = string.splitToSequence("|").map {
        TeamSpeakSingleResponse(it)
    }.toList()
}

