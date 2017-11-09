package com.chancehimmel.notificationserver.services

import com.chancehimmel.notificationserver.entities.AvitarEntity
import com.chancehimmel.notificationserver.entities.SessionEventEntity
import com.chancehimmel.notificationserver.repositories.AvitarRepository
import com.chancehimmel.notificationserver.repositories.SessionEventRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SessionService(@Autowired val avitarRepository: AvitarRepository,
                     @Autowired val sessionEventRepository: SessionEventRepository) {

    fun addSession(avitar: AvitarEntity, sessionEvent: SessionEventEntity) {
        val avi = avitarRepository.findById(avitar.key).orElse(avitar)
        avi.sessionEvents.add(sessionEvent)
        sessionEvent.avitar = avi
        avitarRepository.save(avi)
    }

}