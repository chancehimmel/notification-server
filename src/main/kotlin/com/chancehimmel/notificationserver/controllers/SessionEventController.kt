package com.chancehimmel.notificationserver.controllers

import com.chancehimmel.notificationserver.entities.AvitarEntity
import com.chancehimmel.notificationserver.entities.SessionEventEntity
import com.chancehimmel.notificationserver.resources.SessionEventResource
import com.chancehimmel.notificationserver.services.SessionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class SessionEventController(@Autowired val sessionService: SessionService) {

    @PostMapping("/session")
    fun registerOnline(@RequestHeader("X-SecondLife-Owner-Key") aviKey: String,
                       @RequestBody sessionEvent: SessionEventResource): ResponseEntity<Unit> {
        System.out.println(sessionEvent)

        val avitarEntity = AvitarEntity(key = sessionEvent.key, name = sessionEvent.name)
        val sessionEventEntity = SessionEventEntity(avitar = avitarEntity, eventType = sessionEvent.event)
        sessionService.addSession(avitarEntity, sessionEventEntity)

        return ResponseEntity.accepted().build()
    }

}