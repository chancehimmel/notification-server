package com.chancehimmel.notificationserver.resources

import com.chancehimmel.notificationserver.entities.SessionEventEntity
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include
import org.springframework.hateoas.ResourceSupport
import java.util.*

data class SessionEventResource(val key: UUID, val name: String, val event: String)

@JsonInclude(Include.NON_EMPTY)
data class OutboundEventResource(val eventType: String, val timestamp: Date): ResourceSupport()

fun SessionEventEntity.asResource(withLinks: OutboundEventResource.(SessionEventEntity) -> OutboundEventResource): OutboundEventResource {
    return OutboundEventResource(this.eventType, this.timestamp)
            .withLinks(this)
}