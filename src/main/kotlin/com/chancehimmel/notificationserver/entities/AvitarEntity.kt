package com.chancehimmel.notificationserver.entities

import java.util.*
import javax.persistence.*
import kotlin.collections.ArrayList

@Entity
@Table(name = "Avitar")
data class AvitarEntity(
        @Id
        var key: UUID = UUID.randomUUID(),

        var name: String,

        @OneToMany(mappedBy = "avitar", cascade = arrayOf(CascadeType.ALL), orphanRemoval = true)
        @OrderBy("timestamp ASC")
        var sessionEvents: MutableList<SessionEventEntity> = ArrayList()
) {
    constructor() : this(name = "")

    fun isOnline(): Boolean {
        return if(sessionEvents.isEmpty()) false else sessionEvents.last().eventType == "online"
    }
}