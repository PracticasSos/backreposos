package com.Backend.sos.repository

import com.Backend.sos.model.Lens
import org.springframework.data.jpa.repository.JpaRepository

interface LensRepository: JpaRepository<Lens, Long> {

    fun findById(id: Long?): Lens?
}