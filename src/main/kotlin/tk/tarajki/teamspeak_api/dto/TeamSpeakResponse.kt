package tk.tarajki.teamspeak_api.dto

sealed class TeamSpeakResponse {
    class TeamSpeakEmptyResponse : TeamSpeakResponse()

    class TeamSpeakSingleResponse(
            val data: Map<String, String>
    ) : TeamSpeakResponse() {
        constructor(string: String) : this(string.splitToSequence(" ").map {
            val key = it.takeWhile { char ->
                char != '='
            }
            Pair(key, it.drop(key.length + 1))
        }.toMap())
    }

    data class TeamSpeakListResponse(
            val data: List<TeamSpeakSingleResponse>
    ) : TeamSpeakResponse() {
        constructor(string: String) : this(string.splitToSequence("|").map {
            TeamSpeakSingleResponse(it)
        }.toList())
    }
}

