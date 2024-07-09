package com.Backend.sos.repository

import com.Backend.sos.model.RxFinal
import com.Backend.sos.model.RxUso
import org.springframework.data.jpa.repository.JpaRepository

interface RxFinalRepository: JpaRepository<RxFinal, Long> {

    fun findById (id: Long?): RxFinal?
}