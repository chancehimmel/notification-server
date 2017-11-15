package com.chancehimmel.notificationserver.resources

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include
import org.springframework.hateoas.ResourceSupport
import java.util.*

@JsonInclude(Include.NON_NULL)
data class SessionEventResource(val key: UUID, val name: String, val event: String) : ResourceSupport()
