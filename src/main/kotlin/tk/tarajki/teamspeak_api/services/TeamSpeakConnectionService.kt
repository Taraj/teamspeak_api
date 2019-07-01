package tk.tarajki.teamspeak_api.services

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import tk.tarajki.teamspeak_api.dto.TeamSpeakListResponse
import tk.tarajki.teamspeak_api.dto.TeamSpeakResponse
import tk.tarajki.teamspeak_api.dto.TeamSpeakSingleResponse
import tk.tarajki.teamspeak_api.exceptions.ConnectionError
import tk.tarajki.teamspeak_api.exceptions.TeamSpeakError
import tk.tarajki.teamspeak_api.util.Util
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.Socket
import java.net.SocketException
import kotlin.reflect.KClass

@Service
class TeamSpeakConnectionService {

    @Value("\${config.teamspeak-server-address}")
    private var serverAddress: String = "localhost"

    @Value("\${config.teamspeak-server-port}")
    private var serverPort: Int = 10011

    @Value("\${config.teamspeak-server-id}")
    private var serverId: Int = 1

    @Value("\${config.teamspeak-query-login}")
    private  var login: String = "serveradmin"

    @Value("\${config.teamspeak-query-password}")
    private lateinit var password: String

    @Value("\${config.teamspeak-query-nickname}")
    private  var nickname: String = "API"


    private var connection: Socket? = null

    private var outToServer: DataOutputStream? = null

    private var inFromServer: BufferedReader? = null

    private fun skipServerIntro() {
        inFromServer?.let {
            it.readLine()
            it.readLine()
            it.readLine()
            it.readLine()
        } ?: throw RuntimeException("inFromServer był nullem podczas czytania intra")
    }

    private fun executeInitialCommands() {
        execute("login $login $password\n")
        execute("use $serverId client_nickname=$nickname\n")
    }

    private fun connect() {
        connection = Socket(serverAddress, serverPort)
        connection?.let {
            outToServer = DataOutputStream(it.getOutputStream())
            inFromServer = BufferedReader(InputStreamReader(it.getInputStream()))
        }
        skipServerIntro()
        executeInitialCommands()
    }

    private fun readData(): String {
        return inFromServer?.readLine() ?: throw ConnectionError("inFromServer był nullem podczas czytania")
    }

    private fun sendData(data: String) {
        outToServer?.writeBytes(data) ?: throw ConnectionError("outToServer był nullem podczas pisania")
    }

    private fun readAllData(): List<String> {
        val list = ArrayList<String>()
        do {
            list.add(readData())
        } while (!list.last().startsWith("error"))
        readData()
        return list.filter {
            it.isNotBlank()
        }
    }


    private fun throwIfError(string: String, command: String) {
        val list = string.split(" ").drop(1).map {
            it.takeLastWhile { char -> char != '=' }
        }
        if (list[0] != "0") {
            throw TeamSpeakError(Util.decode(list[1]), Integer.parseInt(list[0]), command)
        }
    }

    @Synchronized
    fun execute(command: String, type: KClass<out TeamSpeakResponse> = TeamSpeakResponse::class): TeamSpeakResponse {
        return try {
            sendData(command)
            val readData = readAllData()
            throwIfError(readData.last(), command)
            return when (type) {
                TeamSpeakSingleResponse::class -> {
                    TeamSpeakSingleResponse(readData.first())
                }
                TeamSpeakListResponse::class -> {
                    TeamSpeakListResponse(readData.first())
                }
                else -> {
                    return TeamSpeakResponse()
                }
            }

        } catch (e: SocketException) {
            connect()
            execute(command, type)
        } catch (e: ConnectionError) {
            connect()
            execute(command, type)
        }
    }
}