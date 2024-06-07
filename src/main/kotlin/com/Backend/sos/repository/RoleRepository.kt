package com.Backend.sos.repository

import com.Backend.sos.model.Roles
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository : JpaRepository<Roles, Long> {
    fun findByRoleName(roleName: String): Roles?
}