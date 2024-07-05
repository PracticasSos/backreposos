package com.Backend.sos.repository

import com.Backend.sos.model.RxUso
import org.springframework.data.jpa.repository.JpaRepository

interface RxUseRepository: JpaRepository<RxUso, Long> {

    fun findById (id: Long?): Long?
}