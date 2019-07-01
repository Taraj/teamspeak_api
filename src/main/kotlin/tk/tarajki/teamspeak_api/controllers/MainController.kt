package tk.tarajki.teamspeak_api.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import tk.tarajki.teamspeak_api.dto.TeamSpeakListResponse
import tk.tarajki.teamspeak_api.dto.TeamSpeakResponse
import tk.tarajki.teamspeak_api.dto.TeamSpeakSingleResponse
import tk.tarajki.teamspeak_api.services.TeamSpeakConnectionService

@RestController
class MainController(
        private val teamSpeakConnectionService: TeamSpeakConnectionService
) {

    @GetMapping("/list")
    fun listGet(@RequestParam command: String): ResponseEntity<TeamSpeakResponse> {
        return ResponseEntity(teamSpeakConnectionService.execute("$command\n", TeamSpeakListResponse::class), HttpStatus.OK)
    }

    @GetMapping("/single")
    fun singleGet(@RequestParam command: String): ResponseEntity<TeamSpeakResponse> {
        return ResponseEntity(teamSpeakConnectionService.execute("$command\n", TeamSpeakSingleResponse::class), HttpStatus.OK)
    }

    @GetMapping("/empty")
    fun emptyGet(@RequestParam command: String): ResponseEntity<TeamSpeakResponse> {
        return ResponseEntity(teamSpeakConnectionService.execute("$command\n", TeamSpeakResponse::class), HttpStatus.OK)
    }


    @PostMapping("/list")
    fun listPost(@RequestBody command: String): ResponseEntity<TeamSpeakResponse> {
        return ResponseEntity(teamSpeakConnectionService.execute("$command\n", TeamSpeakListResponse::class), HttpStatus.OK)
    }

    @PostMapping("/single")
    fun singlePost(@RequestBody command: String): ResponseEntity<TeamSpeakResponse> {
        return ResponseEntity(teamSpeakConnectionService.execute("$command\n", TeamSpeakSingleResponse::class), HttpStatus.OK)
    }

    @PostMapping("/empty")
    fun emptyPost(@RequestBody command: String): ResponseEntity<TeamSpeakResponse> {
        return ResponseEntity(teamSpeakConnectionService.execute("$command\n", TeamSpeakResponse::class), HttpStatus.OK)
    }
}