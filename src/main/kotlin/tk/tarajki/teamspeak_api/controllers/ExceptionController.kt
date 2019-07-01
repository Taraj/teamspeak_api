package tk.tarajki.teamspeak_api.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import tk.tarajki.teamspeak_api.dto.TeamSpeakErrorDto
import tk.tarajki.teamspeak_api.exceptions.TeamSpeakError

@ControllerAdvice
class ExceptionController {
    @ExceptionHandler(TeamSpeakError::class)
    fun handleException(teamSpeakError: TeamSpeakError): ResponseEntity<TeamSpeakErrorDto> {
        return ResponseEntity(TeamSpeakErrorDto(teamSpeakError), HttpStatus.BAD_REQUEST)
    }
}

