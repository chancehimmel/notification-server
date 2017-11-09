package com.chancehimmel.notificationserver.repositories

import com.chancehimmel.notificationserver.entities.SessionEventEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SessionEventRepository : CrudRepository<SessionEventEntity, UUID>