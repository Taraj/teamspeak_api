package tk.tarajki.teamspeak_api.serializers

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import org.springframework.boot.jackson.JsonComponent
import tk.tarajki.teamspeak_api.dto.TeamSpeakResponse
import tk.tarajki.teamspeak_api.dto.TeamSpeakSingleResponse

@JsonComponent
class TeamSpeakSingleResponseSerializer : JsonSerializer<TeamSpeakSingleResponse>() {

    override fun serialize(value: TeamSpeakSingleResponse, gen: JsonGenerator, serializers: SerializerProvider?) {
        gen.writeObject(value.data)
    }
}