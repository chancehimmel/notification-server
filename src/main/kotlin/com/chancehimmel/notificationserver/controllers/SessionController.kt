package com.chancehimmel.notificationserver.controllers

import com.chancehimmel.notificationserver.entities.SessionEntity
import com.chancehimmel.notificationserver.repositories.SessionRepository
import com.chancehimmel.notificationserver.resources.SessionResource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class SessionController(@Autowired val sessionRepository: SessionRepository) {

    @PostMapping("/session")
    fun registerOnline(@RequestHeader("X-SecondLife-Owner-Key") aviKey: String,
                       @RequestBody session: SessionResource): ResponseEntity<Unit> {
        System.out.println(session)

        val sessionEntity = SessionEntity(key = session.key, name = session.name)
        sessionRepository.save(sessionEntity)

        return ResponseEntity.accepted().build()
    }

}