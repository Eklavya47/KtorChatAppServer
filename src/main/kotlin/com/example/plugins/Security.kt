package com.example.plugins

import com.example.sessions.chatSession
import io.ktor.server.application.*
import io.ktor.server.application.ApplicationCallPipeline.ApplicationPhase.Plugins
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.util.*
import kotlinx.coroutines.flow.callbackFlow

fun Application.configureSecurity() {

    install(Sessions) {
        cookie<chatSession>("SESSION")
    }

    intercept(Plugins){
        if (call.sessions.get<chatSession>() == null){
            val userName = call.parameters["userName"] ?: "Guest"
            call.sessions.set(chatSession(userName, generateNonce()))
        }
    }
}
