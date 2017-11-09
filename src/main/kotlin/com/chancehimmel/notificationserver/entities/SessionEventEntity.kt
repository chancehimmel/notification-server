package com.chancehimmel.notificationserver.entities

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "SessionEvent")
class SessionEventEntity(
        @Id var id: UUID = UUID.randomUUID(),
        var timestamp: Date = Date(),
        var eventType: String,
        @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "avitar_id") var avitar: AvitarEntity
)