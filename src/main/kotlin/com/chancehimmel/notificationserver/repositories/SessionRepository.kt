package com.chancehimmel.notificationserver.repositories

import com.chancehimmel.notificationserver.entities.SessionEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SessionRepository : CrudRepository<SessionEntity, UUID>