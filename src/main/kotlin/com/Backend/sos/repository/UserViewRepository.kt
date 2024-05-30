package com.Backend.sos.repository

import com.Backend.sos.model.UserView
import org.springframework.data.jpa.repository.JpaRepository

interface UserViewRepository: JpaRepository<UserView, Long?> {

}