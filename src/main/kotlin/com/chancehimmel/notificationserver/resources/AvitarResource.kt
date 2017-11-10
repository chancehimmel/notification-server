package com.chancehimmel.notificationserver.resources

import com.chancehimmel.notificationserver.entities.AvitarEntity

data class AvitarResource(val name: String, val isOnline: Boolean)

fun AvitarEntity.asResource(): AvitarResource {
    return AvitarResource(this.name, this.isOnline())
}