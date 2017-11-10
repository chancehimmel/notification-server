package com.chancehimmel.notificationserver.controllers

import com.chancehimmel.notificationserver.resources.AvitarResource
import com.chancehimmel.notificationserver.services.AvitarService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class AvitarController(@Autowired val avitarService: AvitarService) {

    val logger = LoggerFactory.getLogger(AvitarController::class.java)

    @GetMapping("/avitar/{id}")
    fun getAvitar(@PathVariable id: UUID): ResponseEntity<AvitarResource> {
        logger.info("Getting avitar: {}", id)
        val avitar = avitarService.findAvitar(id)
        return ResponseEntity.ok(avitar)
    }

}