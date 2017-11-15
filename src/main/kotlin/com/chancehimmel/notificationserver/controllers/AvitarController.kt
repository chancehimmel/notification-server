package com.chancehimmel.notificationserver.controllers

import com.chancehimmel.notificationserver.entities.AvitarEntity
import com.chancehimmel.notificationserver.resources.AvitarResource
import com.chancehimmel.notificationserver.services.AvitarService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/avitars")
class AvitarController(@Autowired val avitarService: AvitarService) {

    val logger = LoggerFactory.getLogger(AvitarController::class.java)

    /**
     * Function that is used to add links to an [AvitarResource]. It can be passed into the service tier as a black
     * box so that there is no direct coupling with the Spring HATEOAS API there.
     */
    val withLinks = fun AvitarResource.(avitar: AvitarEntity): AvitarResource {
        add(linkTo(methodOn(AvitarController::class.java).getAvitar(avitar.key)).withSelfRel())
        add(linkTo(methodOn(SessionController::class.java).getSessionEvents(avitar.key)).withRel("sessions"))
        return this
    }

    /**
     * Get the set of [AvitarResource]s that have been seen. If [onlineOnly] is passed as true, the set will be
     * filtered to only those [AvitarResource]s that are currently online
     */
    @GetMapping
    fun getAllAvitars(@RequestParam onlineOnly: Boolean = false): ResponseEntity<Set<AvitarResource>> {
        logger.info("Getting all avitars")
        val avitars = avitarService.findAllAvitars(withLinks)
        val toReturn = if (onlineOnly) {
            avitars.filter { it.isOnline }.toSet()
        } else {
            avitars
        }
        return ResponseEntity.ok(toReturn)
    }

    /**
     * Get a particular [AvitarResource] by its [UUID]
     */
    @GetMapping("/{id}")
    fun getAvitar(@PathVariable id: UUID): ResponseEntity<AvitarResource> {
        logger.info("Getting avitar: {}", id)
        val avitar = avitarService.findAvitar(id, withLinks)
        return ResponseEntity.ok(avitar)
    }

    @DeleteMapping("/{id}")
    fun deleteAvitar(@PathVariable id: UUID): ResponseEntity<Unit> {
        logger.info("Deleteing avitar: {}", id)
        avitarService.deleteAvitar(id)
        return ResponseEntity.ok().build()
    }

}