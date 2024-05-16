package com.Backend.sos.repository

import com.Backend.sos.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long>{
    fun findById (id: Long?): User?

}