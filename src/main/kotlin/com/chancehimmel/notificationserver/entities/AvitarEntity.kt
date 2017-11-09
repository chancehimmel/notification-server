package com.chancehimmel.notificationserver.entities

import java.util.*
import javax.persistence.*
import kotlin.collections.ArrayList

@Entity
@Table(name = "Avitar")
data class AvitarEntity(
        @Id var key: UUID = UUID.randomUUID(),
        var name: String,
        @OneToMany(mappedBy = "avitar", cascade = arrayOf(CascadeType.ALL), orphanRemoval = true) var sessionEvents: MutableList<SessionEventEntity> = ArrayList()
)