package tk.tarajki.teamspeak_api.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus


@ResponseStatus(value = HttpStatus.BAD_REQUEST)
class TeamSpeakError(
        val msg: String,
        val code: Int,
        val command: String
) : RuntimeException(msg)