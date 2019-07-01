package tk.tarajki.teamspeak_api.dto

import tk.tarajki.teamspeak_api.exceptions.TeamSpeakError

data class TeamSpeakErrorDto (
        val msg: String,
        val code: Int,
        val command: String
){
    constructor(teamSpeakError: TeamSpeakError):this(
            teamSpeakError.msg,
            teamSpeakError.code,
            teamSpeakError.command
    )
}