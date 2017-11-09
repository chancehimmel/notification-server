package com.chancehimmel.notificationserver.repositories

import com.chancehimmel.notificationserver.entities.AvitarEntity
import org.springframework.data.repository.CrudRepository
import java.util.*

interface AvitarRepository : CrudRepository<AvitarEntity, UUID>