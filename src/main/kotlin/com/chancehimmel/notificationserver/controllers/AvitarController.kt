package com.chancehimmel.notificationserver.controllers

import com.chancehimmel.notificationserver.entities.AvitarEntity
import com.chancehimmel.notificationserver.resources.AvitarResource
import com.chancehimmel.notificationserver.services.AvitarService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.ResourceSupport
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/avitars")
class AvitarController(@Autowired val avitarService: AvitarService) {

    val logger = LoggerFactory.getLogger(AvitarController::class.java)

    val withLinks = fun AvitarResource.(avitar: AvitarEntity): AvitarResource {
        add(linkTo(methodOn(AvitarController::class.java).getAvitar(avitar.key)).withSelfRel())
        return this
    }

//    val withLinks = fun AvitarResource.(avitar: AvitarEntity): Unit {
//        add(linkTo(methodOn(AvitarController::class.java).getAvitar(avitar.key)).withSelfRel())
//    }

    @GetMapping
    fun getAllAvitars(): ResponseEntity<Set<AvitarResource>> {
        logger.info("Getting all avitars")
        val avitars = avitarService.findAllAvitars(withLinks)
        return ResponseEntity.ok(avitars)
    }

    @GetMapping("/{id}")
    fun getAvitar(@PathVariable id: UUID): ResponseEntity<AvitarResource> {
        logger.info("Getting avitar: {}", id)
        val avitar = avitarService.findAvitar(id, withLinks)
        return ResponseEntity.ok(avitar)
    }

}