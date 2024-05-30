package com.Backend.sos.repository

import com.Backend.sos.model.Roles
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRespository: JpaRepository <Roles, Long> {

    fun findById (id: Long?): Roles?

}