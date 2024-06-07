package com.Backend.sos.repository

import com.Backend.sos.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long>{

    fun findByUsername(username: String?): User?

}

//hacer una vista con lo que es el login nomas para mostrar