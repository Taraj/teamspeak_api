package tk.tarajki.teamspeak_api.exceptions

class TeamSpeakError(
        val msg: String,
        val code: Int,
        val command: String
) : RuntimeException(msg)