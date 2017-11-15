package com.chancehimmel.notificationserver.resources

import com.chancehimmel.notificationserver.entities.AvitarEntity
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include
import org.springframework.hateoas.ResourceSupport

/**
 * Resource that represnets an Avitar
 */
@JsonInclude(Include.NON_EMPTY)
data class AvitarResource(val name: String, val isOnline: Boolean) : ResourceSupport()

fun AvitarEntity.asResource(withLinks: AvitarResource.(AvitarEntity) -> AvitarResource): AvitarResource {
    return AvitarResource(this.name, this.isOnline())
            .withLinks(this)
}
