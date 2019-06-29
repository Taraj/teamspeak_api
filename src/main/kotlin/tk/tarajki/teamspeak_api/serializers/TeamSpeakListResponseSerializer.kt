package tk.tarajki.teamspeak_api.serializers

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import org.springframework.boot.jackson.JsonComponent
import tk.tarajki.teamspeak_api.dto.TeamSpeakResponse

@JsonComponent
class TeamSpeakListResponseSerializer : JsonSerializer<TeamSpeakResponse.TeamSpeakListResponse>(){

    override fun serialize(value: TeamSpeakResponse.TeamSpeakListResponse, gen: JsonGenerator, serializers: SerializerProvider?) {
        gen.writeObject(value.data)
    }
}