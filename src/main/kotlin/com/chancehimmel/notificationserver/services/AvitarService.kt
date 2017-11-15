package com.chancehimmel.notificationserver.services

import com.chancehimmel.notificationserver.entities.AvitarEntity
import com.chancehimmel.notificationserver.repositories.AvitarRepository
import com.chancehimmel.notificationserver.resources.AvitarResource
import com.chancehimmel.notificationserver.resources.asResource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.ResourceSupport
import org.springframework.stereotype.Service
import java.util.*

@Service
class AvitarService(@Autowired val avitarRepository: AvitarRepository) {

    fun findAllAvitars(withLinks: AvitarResource.(AvitarEntity) -> AvitarResource): Set<AvitarResource> {
        return avitarRepository.findAll()
                .map { it.asResource(withLinks) }
                .toSet()
    }

    fun findAvitar(id: UUID, withLinks: AvitarResource.(AvitarEntity) -> AvitarResource): AvitarResource {
        return avitarRepository.findById(id)
                .map { it.asResource(withLinks) }
                .orElseThrow { RuntimeException() }
    }

}