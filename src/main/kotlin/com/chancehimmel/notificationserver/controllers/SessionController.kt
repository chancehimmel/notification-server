package com.chancehimmel.notificationserver.controllers

import com.chancehimmel.notificationserver.entities.AvitarEntity
import com.chancehimmel.notificationserver.entities.SessionEventEntity
import com.chancehimmel.notificationserver.resources.OutboundEventResource
import com.chancehimmel.notificationserver.resources.SessionEventResource
import com.chancehimmel.notificationserver.services.SessionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api")
class SessionController(@Autowired val sessionService: SessionService) {

    val withLinks = fun OutboundEventResource.(session: SessionEventEntity): OutboundEventResource {
        add(linkTo(methodOn(AvitarController::class.java).getAvitar(session.id)).withSelfRel())
        return this
    }

    @GetMapping("/avitars/{id}/sessions")
    fun getSessionEvents(@PathVariable id: UUID): ResponseEntity<Set<OutboundEventResource>> {
        val sessionEvents = sessionService.getSessions(id, withLinks)
        return ResponseEntity.ok(sessionEvents)
    }

    @PostMapping("/sessions")
    fun addSessionEvent(@RequestBody sessionEvent: SessionEventResource): ResponseEntity<Unit> {
        val avitarEntity = AvitarEntity(key = sessionEvent.key, name = sessionEvent.name)
        val sessionEventEntity = SessionEventEntity(avitar = avitarEntity, eventType = sessionEvent.event)
        sessionService.addSession(avitarEntity, sessionEventEntity)

        return ResponseEntity.accepted().build()
    }

}