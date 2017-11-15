package com.chancehimmel.notificationserver.services

import com.chancehimmel.notificationserver.entities.AvitarEntity
import com.chancehimmel.notificationserver.entities.SessionEventEntity
import com.chancehimmel.notificationserver.repositories.AvitarRepository
import com.chancehimmel.notificationserver.resources.OutboundEventResource
import com.chancehimmel.notificationserver.resources.asResource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class SessionService(@Autowired val avitarRepository: AvitarRepository) {

    fun getSessions(avitarId: UUID,
                    withLinks: OutboundEventResource.(SessionEventEntity) -> OutboundEventResource): Set<OutboundEventResource> {
        val avitar = avitarRepository.findById(avitarId).orElseThrow({ IllegalStateException() })
        return avitar.sessionEvents
                .map { it.asResource(withLinks) }
                .toSet()
    }

    fun addSession(avitar: AvitarEntity, sessionEvent: SessionEventEntity) {
        val avi = avitarRepository.findById(avitar.key).orElse(avitar)
        avi.sessionEvents.add(sessionEvent)
        sessionEvent.avitar = avi
        avitarRepository.save(avi)
    }

}