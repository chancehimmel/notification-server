package com.chancehimmel.notificationserver.services

import com.chancehimmel.notificationserver.repositories.AvitarRepository
import com.chancehimmel.notificationserver.resources.AvitarResource
import com.chancehimmel.notificationserver.resources.asResource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class AvitarService(@Autowired val avitarRepository: AvitarRepository) {

    fun findAvitar(id: UUID): AvitarResource {
        return avitarRepository.findById(id)
                .map { it.asResource() }
                .orElseThrow { RuntimeException() }
    }

}