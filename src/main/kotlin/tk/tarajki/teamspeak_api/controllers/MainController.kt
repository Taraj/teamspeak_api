package tk.tarajki.teamspeak_api.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import tk.tarajki.teamspeak_api.dto.TeamSpeakResponse

@RestController
class MainController {

    @GetMapping("/")
    fun home(): TeamSpeakResponse {
        return TeamSpeakResponse.TeamSpeakListResponse("clid cid=1 client_database_id=1 client_nickname=serveradmin client_type=1|clid=3 cid=2835 client_database_id=1894 client_nickname=Buno client_type=0|clid=5 cid=2835 client_database_id=1905 client_nickname=Korel client_type=0|clid=7 cid=1 client_database_id=7 client_nickname=Taraj client_type=0|clid=9 cid=3255 client_database_id=3790 client_nickname=JulllGame client_type=0|clid=11 cid=20 client_database_id=15 client_nickname=Taraj\\s\\/\\sRex\\sFortis client_type=0|clid=48 cid=1 client_database_id=1 client_nickname=Bot client_type=1")
    }
}