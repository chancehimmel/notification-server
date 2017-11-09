package com.chancehimmel.notificationserver.entities

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "Session")
class SessionEntity(
        @Id var id: UUID = UUID.randomUUID(),
        var key: UUID,
        var name: String?
)